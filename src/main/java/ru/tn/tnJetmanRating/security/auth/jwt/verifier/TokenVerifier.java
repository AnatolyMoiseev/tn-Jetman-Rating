package ru.tn.tnJetmanRating.security.auth.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
