package ru.tn.tnJetmanRating.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tn.tnJetmanRating.persistance.dto.RatingDto;
import ru.tn.tnJetmanRating.persistance.dto.UserDto;
import ru.tn.tnJetmanRating.persistance.model.User;
import ru.tn.tnJetmanRating.persistance.repository.RatingRepository;
import ru.tn.tnJetmanRating.persistance.repository.UserRepository;
import ru.tn.tnJetmanRating.service.RatingService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository dao;
    private UserRepository userRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository dao, UserRepository userRepository) {
        this.dao = dao;
        this.userRepository = userRepository;
    }

    @Override
    public RatingDto getRatingTable() {
        List<UserDto> userDtoList = new ArrayList<>();

        List<User> userList = userRepository.findAll();

        userList.forEach(user -> userDtoList.add(new UserDto(user.getScreenName(), user.getDistance(),
                user.getPosition(), user.getAvatar(), user.getJetpack())));

        return new RatingDto(userDtoList);
    }

}
