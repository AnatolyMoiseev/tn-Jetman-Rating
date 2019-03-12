package ru.tn.tnJetmanRating.persistance.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, Jetpack> jetpack;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, String> screenName;
	public static volatile SingularAttribute<User, Avatar> avatar;

	public static final String PASSWORD = "password";
	public static final String JETPACK = "jetpack";
	public static final String ID = "id";
	public static final String SCREEN_NAME = "screenName";
	public static final String AVATAR = "avatar";

}

