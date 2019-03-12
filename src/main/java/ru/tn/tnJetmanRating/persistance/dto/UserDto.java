package ru.tn.tnJetmanRating.persistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tn.tnJetmanRating.persistance.model.Avatar;
import ru.tn.tnJetmanRating.persistance.model.Jetpack;

@Data
@AllArgsConstructor
public class UserDto {

    private String screenName;

    private String passsword;

    private Avatar avatar;

    private Jetpack jetpack;

}
