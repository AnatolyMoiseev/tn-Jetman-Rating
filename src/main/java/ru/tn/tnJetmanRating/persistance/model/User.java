package ru.tn.tnJetmanRating.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id", unique = true, nullable = false, precision = 15)
    private Long id;

    private String screenName;

    private String password;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "avatar")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Avatar avatar;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "jetpack")
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private Jetpack jetpack;
}
