package ru.tn.tnJetmanRating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tn.tnJetmanRating.exception.ResourceNotFoundException;
import ru.tn.tnJetmanRating.persistance.dto.SignUpDto;
import ru.tn.tnJetmanRating.persistance.dto.UserDto;
import ru.tn.tnJetmanRating.persistance.model.Avatar;
import ru.tn.tnJetmanRating.persistance.model.Jetpack;
import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.persistance.repository.UserRepository;
import ru.tn.tnJetmanRating.service.UserService;
import ru.tn.tnJetmanRating.service.impl.secure.UserDetailsServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDetailsServiceImpl userDetailService;
    private final UserRepository dao;

    @Autowired
    public UserServiceImpl(UserDetailsServiceImpl userDetailService, UserRepository dao) {
        this.userDetailService = userDetailService;
        this.dao = dao;
    }

    @Override
    public User getUserById(Long id) {
        return dao.findOneByUserId(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public List<User> getAllUsers() {
        return dao.findAll();
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return dao.findUserByScreenNameIgnoreCase(userName);
    }

    @Override
    public String signUpUser(SignUpDto signUpDto) {
        UserDetails userDetails;
        try {
            userDetails = userDetailService.loadUserByUsername(signUpDto.getScreenName());
        } catch (UsernameNotFoundException e){
            userDetails = null;
        }
        if (userDetails == null) {
            User user = new User();
            user.setScreenName(signUpDto.getScreenName());
            String encodePassword = new BCryptPasswordEncoder().encode(signUpDto.getPasssword());
            user.setPassword(encodePassword);
            User save = userDetailService.save(user);
            user.setAvatar(new Avatar());
            user.setJetpack(new Jetpack());
            return save.toString();
        }
        return null;
    }


}
