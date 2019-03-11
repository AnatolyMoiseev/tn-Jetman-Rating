package ru.tn.tnJetmanRating.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.tn.tnJetmanRating.config.JwtSettings;
import ru.tn.tnJetmanRating.security.JwtAuthenticationToken;

import java.util.List;
import java.util.stream.Collectors;

@Component
@SuppressWarnings("unchecked")
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtSettings jwtSettings;

    @Autowired
    public JwtAuthenticationProvider(JwtSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();

        Jws<Claims> jwsClaims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
        String subject = jwsClaims.getBody().getSubject();
        List<String> scopes = jwsClaims.getBody().get("scopes", List.class);
        Integer id = jwsClaims.getBody().get("id", Integer.class);
        List<GrantedAuthority> authorities = scopes.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        AuthUser context = id != null ?
                new AuthUserBuilder()
                        .id(Long.valueOf(id))
                        .username(subject)
                        .authorities(authorities)
                        .build() :
                new AuthUserBuilder()
                        .username(subject)
                        .authorities(authorities)
                        .build();

        return new JwtAuthenticationToken(context, context.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
