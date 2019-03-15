package ru.tn.tnJetmanRating.utils;

import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.security.domain.AuthUser;

public class AuthUserUtils {

    public static AuthUser mapToUserDetails(User user) {
        return new AuthUserBuilder()
                .id(user.getId())
                .username(user.getUserName())
                .password(user.getPassword())
                .build();
    }

}
