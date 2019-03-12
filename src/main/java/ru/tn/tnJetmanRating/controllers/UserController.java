package ru.tn.tnJetmanRating.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tn.tnJetmanRating.persistance.repository.UserRepository;
import ru.tn.tnJetmanRating.service.UserService;

import static ru.tn.tnJetmanRating.controllers.ApiController.USER;

@RestController
@RequestMapping(value = USER)
public class UserController implements ApiController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



}
