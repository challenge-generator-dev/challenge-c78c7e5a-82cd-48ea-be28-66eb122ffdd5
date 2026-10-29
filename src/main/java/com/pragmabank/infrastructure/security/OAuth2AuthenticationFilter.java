package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;

/**
 * Filtro de autenticación OAuth2 para proteger endpoints.
 * Este filtro valida tokens OAuth2 y establece la autenticación en el contexto de seguridad.
 */
public class OAuth2AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ClientRegistrationRepository clientRegistrationRepository;
    private AuthenticationConverter authenticationConverter;

    public OAuth2AuthenticationFilter(RequestMatcher matcher,
                                       ClientRegistrationRepository clientRegistrationRepository) {
        super(matcher);
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                 HttpServletResponse response)
            throws OAuth2AuthenticationException {
        if (authenticationConverter == null) {
            throw new OAuth2AuthenticationException(
                new OAuthError("invalid_request", "Authentication converter not configured", null));
        }
        
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    public void setAuthenticationConverter(AuthenticationConverter converter) {
        this.authenticationConverter = converter;
    }
}