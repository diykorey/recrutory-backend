package com.kandidato.security;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.linkedin.connect.LinkedInServiceProvider;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@Controller
@RequestMapping("/auth")
@Api(value = "auth", description = "Handles OAuth2 authentication.")
public class AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    private Environment env;

    @Autowired
    private LinkedInServiceProvider linkedInProvider;

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        removeAuthCookies(response);


        String redirectUrl = env.getProperty("landing");
        return "redirect:" + redirectUrl;
    }

    private void removeAuthCookies(HttpServletResponse response) {
        Cookie cookie = new Cookie("auth-token", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setDomain(".recrutory.com");
        response.addCookie(cookie);
    }


    @RequestMapping(value = "/linkedinAuthorization", method = RequestMethod.GET)
    public String linkedinAuthorization() {
        OAuth2Operations ops = linkedInProvider.getOAuthOperations();

        String redirectUrl = env.getProperty("linkedin.redirectUrl");
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri(redirectUrl);
        params.setScope("r_basicprofile r_emailaddress");
        params.setState("1234567890");

        return "redirect:" + ops.buildAuthorizeUrl(params);
    }

    @RequestMapping(value = "/linkedinAccessToken", method = RequestMethod.GET)
    public String linkedinAccessToken(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code) throws IOException {

        OAuth2Operations ops = linkedInProvider.getOAuthOperations();
        String redirectUrl = env.getProperty("linkedin.redirectUrl");
        String homeUrl = env.getProperty("app.home");

        try {
            AccessGrant access = ops.exchangeForAccess(code, redirectUrl, new LinkedMultiValueMap<>());
            setAuthCookies(access, response);
            return "redirect:" + homeUrl;
        } catch (HttpClientErrorException e) {
            String error = e.getResponseBodyAsString();
            return "redirect:" + homeUrl + "?error=" + error;
        }
    }

    private void setAuthCookies(AccessGrant access, HttpServletResponse response) {
        String token = encodeToken(access);
        log.debug("Token: {}", token);
        Cookie cookie = new Cookie("auth-token", token);
        cookie.setMaxAge(-1);
        //cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain(".recrutory.com");
        response.addCookie(cookie);
    }

    private String encodeToken(AccessGrant access) {
        String token = access.getAccessToken();
        byte[] tokenBytes = ("linkedIn=" + token).getBytes();
        try {
            return URLEncoder.encode(Base64.getEncoder().encodeToString(tokenBytes), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            log.warn("Cannot encode token: {}", token);
            return "";
        }
    }
}