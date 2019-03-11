package ru.tn.tnJetmanRating.security.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.tn.tnJetmanRating.config.SecurityConfig.AUTH_HEADER_NAME;
import static ru.tn.tnJetmanRating.config.SecurityConfig.REFRESH_TOKEN;

@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenFactory tokenFactory;

    @Autowired
    public AjaxAwareAuthenticationSuccessHandler(final JwtTokenFactory tokenFactory) {
        this.tokenFactory = tokenFactory;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        AuthUser principal = (AuthUser) authentication.getPrincipal();
        JwtToken accessToken = tokenFactory.createAccessJwtToken(principal);
        JwtToken refreshToken = tokenFactory.createRefreshToken(principal);
        response.addHeader(AUTH_HEADER_NAME, accessToken.getToken());
        response.addHeader(REFRESH_TOKEN, refreshToken.getToken());
        response.addHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Allow-Methods", "*");
        clearAuthenticationAttributes(request);
    }

    /**
     * Removes temporary authentication-related data which may have been stored
     * in the session during the authentication process..
     */
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return;
        }

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
