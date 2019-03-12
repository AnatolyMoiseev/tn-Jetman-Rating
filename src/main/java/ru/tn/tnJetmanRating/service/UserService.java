package ru.tn.tnJetmanRating.service;

import ru.tn.tnJetmanRating.persistance.model.User;

import java.util.Optional;

public interface UserService {

    Long acceptUser(Long id);

    User getCurrentUser(Long userId);

    Optional<User> getUserByUserName(String username);

}
