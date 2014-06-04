package com.kandidato.security;

import org.springframework.security.crypto.codec.Base64;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * The purpose of this filter is to provide authentication data to every request, so that access to the application can be accessed without, without performing the login.
 * <p/>
 * This filter should be disabled for non-development environments.
 * <p/>
 * Created by andriy on 5/11/14.
 */
public class DummyAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        DummyAuthenticationRequest requestWrapper = new DummyAuthenticationRequest(httpRequest);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {

    }

    /**
     * Request wrapper, which provides authentication details, if they are not already present in the request.
     */
    private class DummyAuthenticationRequest extends HttpServletRequestWrapper {

        private static final String AUTH_HEADER_NAME = "Authorization";

        private final String AUTH_DATA;

        private DummyAuthenticationRequest(HttpServletRequest request) {
            super(request);
            byte[] authData = Base64.encode("user:user".getBytes());
            this.AUTH_DATA = "Basic " + new String(authData);
        }

        @Override
        public String getHeader(String name) {
            if (AUTH_HEADER_NAME.equals(name) && null == super.getHeader(name)) {
                return AUTH_DATA;
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            names.addAll(Collections.list(super.getParameterNames()));
            if (!names.contains(AUTH_HEADER_NAME)) {
                names.add(AUTH_HEADER_NAME);
            }
            return Collections.enumeration(names);
        }
    }
}
