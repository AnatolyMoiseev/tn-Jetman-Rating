package ru.tn.tnJetmanRating.persistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tn.tnJetmanRating.persistance.model.Avatar;
import ru.tn.tnJetmanRating.persistance.model.Jetpack;
import ru.tn.tnJetmanRating.persistance.model.User;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private String screenName;

    private Integer distance;

    private Integer position;

    private Integer level;

    private Avatar avatar;

    private Jetpack jetpack;


}
