package ru.tn.tnJetmanRating.utils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.security.domain.AuthUser;

import java.util.stream.Collectors;

public class AuthUserUtils {

    public static AuthUser mapToUserDetails(User user) {
        return new AuthUserBuilder()
                .id(user.getId())
                .username(user.getScreenName())
                .password(user.getPassword())
                .build();
    }

}
