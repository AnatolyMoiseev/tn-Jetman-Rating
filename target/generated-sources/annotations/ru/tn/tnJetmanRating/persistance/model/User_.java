package ru.tn.tnJetmanRating.persistance.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Integer> distance;
	public static volatile SingularAttribute<User, Integer> level;
	public static volatile SingularAttribute<User, Jetpack> jetpack;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> screenName;
	public static volatile SingularAttribute<User, Integer> position;
	public static volatile SingularAttribute<User, Avatar> avatar;

	public static final String PASSWORD = "password";
	public static final String DISTANCE = "distance";
	public static final String LEVEL = "level";
	public static final String JETPACK = "jetpack";
	public static final String ID = "id";
	public static final String SCREEN_NAME = "screenName";
	public static final String POSITION = "position";
	public static final String AVATAR = "avatar";

}

