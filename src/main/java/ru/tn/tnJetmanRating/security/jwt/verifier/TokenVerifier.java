package ru.tn.tnJetmanRating.security.jwt.verifier;

public interface TokenVerifier {
    public boolean verify(String jti);
}
