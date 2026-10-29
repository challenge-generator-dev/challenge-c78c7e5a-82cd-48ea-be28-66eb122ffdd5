package com.pragmabank.infrastructure.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private Authentication authentication;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenUtil, authenticationManager);
        SecurityContextHolder.clearContext();
    }

    @Test
    void doFilterInternal_withValidToken_shouldAuthenticateUser() throws Exception {
        // Arrange
        String validToken = "Bearer valid.jwt.token";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn(validToken);
        when(jwtTokenUtil.extractUsername(validToken.substring(7))).thenReturn(username);
        when(jwtTokenUtil.validateToken(validToken.substring(7), username)).thenReturn(true);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void doFilterInternal_withNoAuthHeader_shouldNotAuthenticate() throws Exception {
        // Arrange
        when(request.getHeader("Authorization")).thenReturn(null);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void doFilterInternal_withInvalidToken_shouldNotAuthenticate() throws Exception {
        // Arrange
        String invalidToken = "Bearer invalid.token";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn(invalidToken);
        when(jwtTokenUtil.extractUsername(invalidToken.substring(7))).thenReturn(username);
        when(jwtTokenUtil.validateToken(invalidToken.substring(7), username)).thenReturn(false);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void shouldNotFilter_internalEndpoints_shouldSkipFilter() {
        // Arrange
        when(request.getRequestURI()).thenReturn("/actuator/health");
        
        // Act
        boolean result = jwtAuthenticationFilter.shouldNotFilter(request);
        
        // Assert
        // El estudiante debe completar esta sección
    }

    @Test
    void doFilterInternal_withExpiredToken_shouldNotAuthenticate() throws Exception {
        // Arrange
        String expiredToken = "Bearer expired.jwt.token";
        String username = "testuser";
        
        when(request.getHeader("Authorization")).thenReturn(expiredToken);
        when(jwtTokenUtil.extractUsername(expiredToken.substring(7))).thenReturn(username);
        when(jwtTokenUtil.validateToken(expiredToken.substring(7), username)).thenReturn(false);
        
        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        
        // Assert
        // El estudiante debe completar esta sección
    }
}