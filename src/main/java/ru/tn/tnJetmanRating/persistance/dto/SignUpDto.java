package ru.tn.tnJetmanRating.persistance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {

    private String userName;

    private String password;

}
