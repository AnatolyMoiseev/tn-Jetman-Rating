package ru.tn.tnJetmanRating.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tn_user")
@Data
public class User {

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id", unique = true, nullable = false, precision = 15)
    private Long id;

    private String screenName;

    private String password;

    private Integer distance;

    private Integer position;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    //@JsonIgnore
    private Avatar avatar;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    //@JsonIgnore
    private Jetpack jetpack;
}
