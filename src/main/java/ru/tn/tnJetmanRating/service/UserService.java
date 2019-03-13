package ru.tn.tnJetmanRating.service;

import ru.tn.tnJetmanRating.persistance.dto.SignUpDto;
import ru.tn.tnJetmanRating.persistance.dto.UserDto;
import ru.tn.tnJetmanRating.persistance.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String signUpUser(SignUpDto signUpDto);

    User updateUser(User user);

    User getUserById(Long id);

    void deleteUser(Long id);

    List<User> getAllUsers();

    Optional<User> getUserByUserName(String username);

}
