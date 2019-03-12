package ru.tn.tnJetmanRating.persistance.model.token;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import ru.tn.tnJetmanRating.exception.JwtExpiredTokenException;

@Slf4j
public class RawAccessJwtToken implements JwtToken {

    private String token;

    public RawAccessJwtToken(String token) {
        this.token = token;
    }

    public Jws<Claims> parseClaims(String signingKey) {
        try {
            log.info("Token from header:\n{}", this.token);
            return Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(this.token);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            log.error("Invalid JWT Token.{}", e);
            throw new BadCredentialsException("Invalid JWT token: ", e);
        } catch (ExpiredJwtException e) {
            log.info("JWT Token is expired.{}", e);
            throw new JwtExpiredTokenException(this, "JWT Token expired", e);
        }
    }

    @Override
    public String getToken() {
        return token;
    }
}
