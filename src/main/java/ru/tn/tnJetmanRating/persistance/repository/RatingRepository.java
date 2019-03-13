package ru.tn.tnJetmanRating.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tn.tnJetmanRating.persistance.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
