package ru.tn.tnJetmanRating.service.impl.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.tn.tnJetmanRating.config.JwtSettings;
import ru.tn.tnJetmanRating.exception.InvalidJwtToken;
import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.persistance.model.token.*;
import ru.tn.tnJetmanRating.security.auth.jwt.verifier.TokenVerifier;
import ru.tn.tnJetmanRating.security.domain.AuthUser;
import ru.tn.tnJetmanRating.service.UserService;
import ru.tn.tnJetmanRating.utils.AuthUserBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.tn.tnJetmanRating.config.SecurityConfig.AUTH_HEADER_NAME;
import static ru.tn.tnJetmanRating.config.SecurityConfig.REFRESH_TOKEN;

@Service
public class RefreshTokenService {

    private final JwtTokenFactory tokenFactory;
    private final JwtSettings jwtSettings;
    private final UserService userService;
    private final TokenVerifier tokenVerifier;

    @Autowired
    public RefreshTokenService(JwtTokenFactory tokenFactory, JwtSettings jwtSettings, UserService userService, TokenVerifier tokenVerifier) {
        this.tokenFactory = tokenFactory;
        this.jwtSettings = jwtSettings;
        this.userService = userService;
        this.tokenVerifier = tokenVerifier;
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String tokenPayload = request.getHeader(AUTH_HEADER_NAME);

        RawAccessJwtToken rawToken = new RawAccessJwtToken(tokenPayload);
        RefreshToken refreshToken = RefreshToken.create(rawToken, jwtSettings.getTokenSigningKey()).orElseThrow(InvalidJwtToken::new);

        String jti = refreshToken.getJti();
        if (!tokenVerifier.verify(jti)) {
            throw new InvalidJwtToken();
        }

        String subject = refreshToken.getSubject();
        User user = userService.getUserByUserName(subject).orElseThrow(() -> new UsernameNotFoundException("User not found: " + subject));


        AuthUser userContext = new AuthUserBuilder()
                .id(user.getId())
                .username(user.getUserName())
                .build();

        AccessJwtToken accessToken = tokenFactory.createAccessJwtToken(userContext);
        JwtToken refreshToken1 = tokenFactory.createRefreshToken(userContext);
        response.addHeader(AUTH_HEADER_NAME, accessToken.getToken());
        response.addHeader(REFRESH_TOKEN, refreshToken1.getToken());
    }
}
