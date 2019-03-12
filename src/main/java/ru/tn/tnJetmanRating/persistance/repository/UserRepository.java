package ru.tn.tnJetmanRating.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tn.tnJetmanRating.persistance.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByScreenNameIgnoreCase(String screenName);

    User findUserByScreenName(String screenName);

    Optional<User> findOneByUserId(Long id);

}
