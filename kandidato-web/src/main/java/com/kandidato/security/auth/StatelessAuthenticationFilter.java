package com.kandidato.security.auth;


import com.kandidato.manager.candidate.CandidateManagerImpl;
import com.kandidato.manager.user.UserManager;
import com.kandidato.security.model.AuthenticationImpl;
import com.kandidato.security.model.CurrentProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

@Component("authenticationFilter")
public class StatelessAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTH_TOKEN_HEADER = "Auth-Token";
    private static final Logger log = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);
    @Autowired
    private UserManager userManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
        if (RequestMethod.OPTIONS.name().equals(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }
        if (request.getServletPath().startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<CurrentProfile> profile = tryGetCurrentUser(request);

        if (profile.isPresent()) {
            Authentication authentication = new AuthenticationImpl(profile.get());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            response.sendError(401);
        }
    }

    //http://localhost:8080/recrutory/auth/linkedinAccessToken?code=AQSzWNEykxWQbCqws9pvqHCiv7tbL8nPpAsABN0gF_2FGjmYLSuFpDZGv9FpV4uSe9AAKdSZY-H8I6tCrgP-B-AGSmnTHhYkaTbVn7dGA0y5qWCUZcA&state=1234567890#!
    private Optional<CurrentProfile> tryGetCurrentUser(HttpServletRequest request) {
        String authHeader = "bGlua2VkSW49QVFWWkNoV2FsdVhobUhZYWJtN1ZaNGYyRnE0NUx0TnZibFp0N3Z1c3lBbExUdjRjV1gxNlJjeU1PM01DaGtvaGRTN2xJWGpsTV9DX3F3TE1QY2NFS3Zpc25aTXFMYWVXZGpQWkNQR0RGT0ZJNXZWb2JrdllxWnpRQ3NxNU9zT01SZWlmMXB1SDRra1ZEQkZsVjJodHRNcE56UlY1RjY5SzAweFpEcUZLSUhOVFpYWF9uY0E=";
        //String authHeader = request.getHeader(AUTH_TOKEN_HEADER);
        log.info("Authheader: {}", authHeader);
        if (authHeader == null) {
            log.warn("Empty auth-header. Query: {} Header names: {}", request.getQueryString(), request.getHeaderNames());
            return Optional.empty();
        }

        String token = decodeToken(authHeader);
        log.debug("Decoded token: {}", token);
        String[] authInfo = token.split("=");
        log.debug("AuthInfo: {}", Arrays.toString(authInfo));
        if (authInfo.length != 2) {
            log.warn("Wrong token. Query: {} Token: {}", request.getQueryString(),  Arrays.toString(authInfo));
            return Optional.empty();
        }
        return userManager.getCurrentProfile(authInfo[0], authInfo[1]);
    }

    private String decodeToken(String token) {
        byte[] tokenBytes = Base64.getDecoder().decode(token);
        return new String(tokenBytes);
    }
}