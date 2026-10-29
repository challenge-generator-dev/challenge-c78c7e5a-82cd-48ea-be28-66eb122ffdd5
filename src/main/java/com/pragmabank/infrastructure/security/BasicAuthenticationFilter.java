package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Filtro de autenticación básica para proteger endpoints.
 * Este filtro valida credenciales en formato Basic Auth y establece la autenticación.
 */
public class BasicAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;

    public BasicAuthenticationFilter(RequestMatcher matcher, AuthenticationManager authenticationManager) {
        super(matcher, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                 HttpServletResponse response)
            throws AuthenticationException {
        String header = request.getHeader("Authorization");
        
        if (header == null || !header.startsWith("Basic ")) {
            return null;
        }
        
        try {
            String base64Credentials = header.substring(6);
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                                           StandardCharsets.UTF_8);
            
            String[] parts = credentials.split(":", 2);
            if (parts.length != 2) {
                return null;
            }
            
            String username = parts[0];
            String password = parts[1];
            
            UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(username, password);
            
            return getAuthenticationManager().authenticate(authRequest);
            
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        if (successHandler != null) {
            successHandler.onAuthenticationSuccess(request, response, authResult);
        }
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        if (failureHandler != null) {
            failureHandler.onAuthenticationFailure(request, response, failed);
        }
    }

    public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }
}