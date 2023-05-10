package com.example.assignment.Repository;

import com.example.assignment.Model.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity, String> {
    /* Repositories are responsible for the connectivity with database and to tweak around with it. */
}
