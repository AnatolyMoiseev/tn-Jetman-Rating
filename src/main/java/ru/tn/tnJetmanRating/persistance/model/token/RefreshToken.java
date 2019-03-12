package ru.tn.tnJetmanRating.persistance.model.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import ru.tn.tnJetmanRating.enums.Scopes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RefreshToken implements JwtToken {

    private Jws<Claims> claims;

    private RefreshToken(Jws<Claims> claims) {
        this.claims = claims;
    }

    public static Optional<RefreshToken> create(RawAccessJwtToken token, String signingKey) {
        Jws<Claims> claims = token.parseClaims(signingKey);

        List<?> rawScopes = claims.getBody().get("scopes", List.class);
        if (rawScopes != null && !rawScopes.isEmpty()) {
            List<String> scopes = rawScopes.stream()
                    .filter(s -> s instanceof String)
                    .map(s -> (String) s)
                    .collect(Collectors.toList());
            boolean isRefreshTokenExists = scopes.stream()
                    .anyMatch(scope -> Scopes.REFRESH_TOKEN.authority().equals(scope));
            if (!isRefreshTokenExists) {
                return Optional.empty();
            }
        }
        return Optional.of(new RefreshToken(claims));
    }

    @Override
    public String getToken() {
        return null;
    }

    public Jws<Claims> getClaims() {
        return claims;
    }

    public String getJti() {
        return claims.getBody().getId();
    }

    public String getSubject() {
        return claims.getBody().getSubject();
    }

}
