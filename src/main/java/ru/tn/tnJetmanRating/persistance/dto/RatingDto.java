package ru.tn.tnJetmanRating.persistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {

    private List<UserDto> userDto;

}
