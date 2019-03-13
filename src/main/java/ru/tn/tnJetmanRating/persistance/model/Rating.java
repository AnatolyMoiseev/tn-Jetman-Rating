package ru.tn.tnJetmanRating.persistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rating")
@Data
public class Rating {

    @Id
    @GenericGenerator(name = "gen", strategy = "increment")
    @GeneratedValue(generator = "gen")
    @Column(name = "id", unique = true, nullable = false, precision = 15)
    private int id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    //@JsonIgnore
    private List<User> users;


}
