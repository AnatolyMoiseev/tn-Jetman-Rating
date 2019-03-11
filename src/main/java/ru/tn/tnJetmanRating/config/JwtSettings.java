package ru.tn.tnJetmanRating.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "web.security.jwt")
public class JwtSettings {

    private Integer tokenExpirationTime;
    private Integer tokenRefreshExpirationTime;
    private String tokenIssuer;
    private String tokenSigningKey;
}