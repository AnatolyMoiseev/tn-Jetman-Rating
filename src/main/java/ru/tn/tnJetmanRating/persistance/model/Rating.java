package ru.tn.tnJetmanRating.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id", unique = true, nullable = false, precision = 15)
    private int id;

    private int position;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<User> users;


}
