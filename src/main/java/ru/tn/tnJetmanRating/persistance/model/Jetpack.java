package ru.tn.tnJetmanRating.persistance.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "jetpack")
public class Jetpack {

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id", unique = true, nullable = false, precision = 15)
    private int id;

    private int level;

}
