package com.kandidato.service.auth;

import java.awt.*;
import java.net.URI;
import com.google.gson.Gson;
import com.kandidato.core.ConnectionsResponce;
import com.kandidato.core.Person;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class AuthService {

    private static final Logger log = Logger.getLogger(AuthService.class.getName());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void login(HttpServletRequest request, HttpServletResponse response) throws URISyntaxException, IOException {

        String state = UUID.randomUUID().toString();

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("response_type", "code"));
        params.add(new BasicNameValuePair("client_id", "77kujcwn0y8old"));
        params.add(new BasicNameValuePair("state", state));
        params.add(new BasicNameValuePair("redirect_uri", "http://kandidato-1.appspot.com/auth"));

        String url = new URIBuilder("https://www.linkedin.com/uas/oauth2/authorization")
                .setParameters(params).build().toString();

        log.warning(url);

        request.getSession().setAttribute("STATE", state);
        response.sendRedirect(url);
    }

    ;

    @RequestMapping("/auth")
    @ResponseBody
    public String auth(@RequestParam(value = "code", required = false) String code,
                       @RequestParam(value = "state", required = true) String state,
                       @RequestParam(value = "error", required = false) String error,
                       @RequestParam(value = "error_description", required = false) String error_description,
                       HttpServletRequest request,
                       HttpServletResponse response) throws URISyntaxException, IOException {

        String savedState = (String) request.getSession().getAttribute("STATE");

        if (state.equals(savedState)) {
            if (error == null) {
                try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                    AccessToken token = getAccessToken(httpClient, code);
                    ConnectionsResponce connections = getConnections(httpClient, token);

                    StringBuilder builder = new StringBuilder("<html><body><ul>");

                    for(Person person : connections.getValues()){
                        builder.append("<li>" + person + "</li>");
                    }
                    builder.append("</ul></body></html>");
                    response.setHeader("Content-Type", "text/html");
                    return builder.toString();
                }
            } else {
                log.severe(error_description);
                return error_description;
            }
        } else {
            log.severe("State is different. Someone tries to hack us.");
            return "State is different. Someone tries to hack us.";
        }
    }
    private ConnectionsResponce getConnections(CloseableHttpClient httpClient, AccessToken token) throws IOException, URISyntaxException {

        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("oauth2_access_token", token.getAccessToken()));
        log.info("Token:" + token.getAccessToken());
        URI url = new URIBuilder("https://api.linkedin.com/v1/people/~/connections:(first-name,last-name)")
                .setParameters(params).build();

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


    private AccessToken getAccessToken(CloseableHttpClient httpClient, String code) throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("redirect_uri", "http://kandidato-1.appspot.com/auth"));
        params.add(new BasicNameValuePair("client_id", "77kujcwn0y8old"));
        params.add(new BasicNameValuePair("client_secret", "JjK9c5nO6v1AnMF4"));

        HttpPost httpPost = new HttpPost("https://www.linkedin.com/uas/oauth2/accessToken");
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        String json = "";

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity entity = response.getEntity();

            // do something useful with the response body
            json = EntityUtils.toString(entity);

            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }

        Gson gson = new Gson();
        return gson.fromJson(json, AccessToken.class);
    }
}
