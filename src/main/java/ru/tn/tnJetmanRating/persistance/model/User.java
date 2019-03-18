package ru.tn.tnJetmanRating.persistance.model;

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

    private String userName;

    private String password;

    private Integer distance;

    private Integer position;

    private Integer level;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "jetpack_id")
    private Jetpack jetpack;
}
