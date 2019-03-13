package ru.tn.tnJetmanRating.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tn.tnJetmanRating.persistance.dto.SignUpDto;
import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.service.UserService;

import static ru.tn.tnJetmanRating.controllers.ApiController.USER;

@Slf4j
@RestController
@RequestMapping(value = USER)
public class UserController implements ApiController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public HttpStatus signUp(@RequestBody SignUpDto signUpDto){
        return userService.signUpUser(signUpDto) != null ? HttpStatus.CREATED : HttpStatus.FORBIDDEN;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUser(@PathVariable long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping(value = "/all")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/{userName}")
    public ResponseEntity getUserByUserName(@PathVariable String userName) {
        return ResponseEntity.ok(userService.getUserByUserName(userName));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User userDto){
        return ResponseEntity.ok(userService.updateUser(userDto));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return HttpStatus.OK;
    }

}
