package ru.tn.tnJetmanRating.persistance.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Rating.class)
public abstract class Rating_ {

	public static volatile SingularAttribute<Rating, Integer> id;
	public static volatile ListAttribute<Rating, User> users;

	public static final String ID = "id";
	public static final String USERS = "users";

}

