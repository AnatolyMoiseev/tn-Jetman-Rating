package ru.tn.tnJetmanRating.utils;

import org.springframework.security.core.GrantedAuthority;
import ru.tn.tnJetmanRating.security.domain.AuthUser;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AuthUserBuilder {

    private AuthUser authUser;

    public AuthUserBuilder id(Long id) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setId(id);
        return this;
    }

    public AuthUserBuilder username(String username) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setUsername(username);
        return this;
    }

    public AuthUserBuilder password(String password) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setPassword(password);
        return this;
    }

    public AuthUserBuilder expires(Date expires) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setExpires(expires);
        return this;
    }

    public AuthUserBuilder accountExpired(boolean accountExpired) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setAccountExpired(accountExpired);
        return this;
    }

    public AuthUserBuilder accountLocked(boolean accountLocked) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setAccountLocked(accountLocked);
        return this;
    }

    public AuthUserBuilder credentialsExpired(boolean credentialsExpired) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setCredentialsExpired(credentialsExpired);
        return this;
    }

    public AuthUserBuilder accountEnabled(boolean accountEnabled) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setAccountEnabled(accountEnabled);
        return this;
    }

    public AuthUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
        if (authUser == null) {
            authUser = new AuthUser();
        }
        authUser.setAuthorities((List<GrantedAuthority>) authorities);
        return this;
    }

    public AuthUser build() {
        return authUser;
    }

}
