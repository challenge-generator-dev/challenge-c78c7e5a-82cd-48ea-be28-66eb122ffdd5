package com.pragmabank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {
    private final ApplicationProperties applicationProperties;

    public Application(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        validateProperties();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    private void validateProperties() {
        if (applicationProperties.getJwt().getSecret() == null || applicationProperties.getJwt().getSecret().isBlank()) {
            throw new IllegalStateException("La clave secreta JWT no puede estar vacía");
        }
        if (applicationProperties.getJwt().getExpiration() <= 0) {
            throw new IllegalStateException("El tiempo de expiración JWT debe ser positivo");
        }
        if (applicationProperties.getDatasource().getUrl() == null || applicationProperties.getDatasource().getUrl().isBlank()) {
            throw new IllegalStateException("La URL del datasource no puede estar vacía");
        }
    }

    public record ApplicationProperties(
            JwtProperties jwt,
            DatasourceProperties datasource
    ) {
        public record JwtProperties(String secret, long expiration) {}
        public record DatasourceProperties(String url, String username, String password) {}
    }
}