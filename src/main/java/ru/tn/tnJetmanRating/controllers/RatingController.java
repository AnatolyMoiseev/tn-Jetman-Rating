package ru.tn.tnJetmanRating.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tn.tnJetmanRating.service.RatingService;

import static ru.tn.tnJetmanRating.controllers.ApiController.RATING;

@Slf4j
@RestController
@RequestMapping(value = RATING)
public class RatingController {

    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(value = "/table")
    public ResponseEntity getRatingTable() {
        return ResponseEntity.ok(ratingService.getRatingTable());
    }

}
