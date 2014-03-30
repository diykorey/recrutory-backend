package com.kandidato.service.linkedin.auth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.linkedin.connect.LinkedInConnectionFactory;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

@Controller
public class LinkedInAuthService {

  private static final Logger LOG = Logger.getLogger(LinkedInAuthService.class.getName());
  private static final String REDIRECT_URL = "http://kandidato-1.appspot.com/callback";
  @Inject
  private ConnectionFactoryLocator locator;
  @Inject
  private ConnectionRepository connectionRepository;

  @RequestMapping(value = "/auth", method = RequestMethod.GET)
  public void signIn(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {

    String state = UUID.randomUUID().toString();

    LinkedInConnectionFactory linkedInConnectionFactory = (LinkedInConnectionFactory) locator.getConnectionFactory(LinkedIn.class);
    OAuth2Operations oauthOperations = linkedInConnectionFactory.getOAuthOperations();

    OAuth2Parameters params = new OAuth2Parameters();
    params.setRedirectUri(REDIRECT_URL);
    params.setState(state);

    request.getSession().setAttribute("STATE", state);

    String authorizeUrl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, params);
    LOG.warning(authorizeUrl);

    response.sendRedirect(authorizeUrl);
  }

  @RequestMapping(value = "/callback", method = RequestMethod.GET)
  @ResponseBody
  public String callback(@RequestParam(value = "code", required = false) String code,//
      @RequestParam(value = "state", required = true) String state,//
      @RequestParam(value = "error", required = false) String error, //
      @RequestParam(value = "error_description", required = false) String error_description, //
      HttpServletRequest request, //
      HttpServletResponse response) throws URISyntaxException, IOException {

    String savedState = (String) request.getSession().getAttribute("STATE");

    LinkedInConnectionFactory linkedInConnectionFactory = (LinkedInConnectionFactory) locator.getConnectionFactory(LinkedIn.class);
    if (state.equals(savedState)) {
      if (error == null) {
        // OAuth2Operations oauthOperations = linkedInConnectionFactory.getOAuthOperations();
        // AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, REDIRECT_URL, null);
        // Connection<LinkedIn> linkedInConnection = linkedInConnectionFactory.createConnection(accessGrant);
        // /connectionRepository.addConnection(linkedInConnection);

        // This is workaround for AppEngine
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
          Token token = getAccessGrant(httpClient, code);
          ConnectionsResponce connections = getConnections(httpClient, token);

          StringBuilder builder = new StringBuilder("<html><body><ul>");

          for (Person person : connections.getValues()) {
            builder.append("<li>" + person + "</li>");
          }
          builder.append("</ul></body></html>");
          response.setHeader("Content-Type", "text/html");
          return builder.toString();
        }
      } else {
        LOG.severe(error_description);
        return error_description;
      }
    } else {
      LOG.severe("State is different. Someone tries to hack us.");
      return "State is different. Someone tries to hack us.";
    }
  }

  private Token getAccessGrant(CloseableHttpClient httpClient, String code) throws IOException {
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("grant_type", "authorization_code"));
    params.add(new BasicNameValuePair("code", code));
    params.add(new BasicNameValuePair("redirect_uri", REDIRECT_URL));
    params.add(new BasicNameValuePair("client_id", "77kujcwn0y8old"));
    params.add(new BasicNameValuePair("client_secret", "JjK9c5nO6v1AnMF4"));

    HttpPost httpPost = new HttpPost("https://www.linkedin.com/uas/oauth2/accessToken");
    httpPost.setEntity(new UrlEncodedFormEntity(params));

    String json = "";
    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
      HttpEntity entity = response.getEntity();

      // do something useful with the response body
      json = EntityUtils.toString(entity);
      LOG.info("Token Json is: " + json);
      // and ensure it is fully consumed
      EntityUtils.consume(entity);
    }

    return new Gson().fromJson(json, Token.class);
  }

  private ConnectionsResponce getConnections(CloseableHttpClient httpClient, Token token) throws IOException, URISyntaxException {

    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("oauth2_access_token", token.getAccessToken()));
    LOG.info("Token:" + token.getAccessToken());
    URI url = new URIBuilder("https://api.linkedin.com/v1/people/~/connections:(first-name,last-name)").setParameters(params).build();

    HttpGet httpGet = new HttpGet(url);
    httpGet.setHeader("x-li-format", "json");

    String connectionsJson = "";

    try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
      HttpEntity entity = response.getEntity();

      // do something useful with the response body
      connectionsJson = EntityUtils.toString(entity);

      // and ensure it is fully consumed
      EntityUtils.consume(entity);
    }

    Gson gson = new Gson();
    return gson.fromJson(connectionsJson, ConnectionsResponce.class);
  }
}

class Token {
  @SerializedName("expires_in")
  private int expiresIn;

  @SerializedName("access_token")
  private String accessToken;

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}

class ConnectionsResponce {

  private List<Person> values;

  public List<Person> getValues() {
    return values;
  }

  public void setValues(List<Person> values) {
    this.values = values;
  }
}

class Person {
  private String firstName;
  private String lastName;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String toString() {
    return firstName + " " + lastName;
  }
}