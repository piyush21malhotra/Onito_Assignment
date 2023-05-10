package com.example.assignment.Repository;

import com.example.assignment.Model.MovieEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity, String> {

    /* Repositories are responsible for the connectivity with database and to tweak around with it. */

    //  Query to get the longest duration movies from the database.
    @Query(value = " SELECT * FROM movies m ORDER BY m.runtimeMinutes DESC LIMIT 10", nativeQuery = true)
    List<MovieEntity> getTop10Movies();

    //    Query to extract the Top rated movies in terms of averageRating > 6.0
    @Query(value = " Select  m.tconst, m.primarytitle, m.genres, r.averagerating " +
            "FROM movies m INNER JOIN ratings r ON m.tconst = r.tconst" +
            " where r.averagerating > 6.0", nativeQuery = true)
    List<Object[]> getTopRatedMovie();

    //    Query to get the movies by genre, primaryTitle, numVotes with the subtotal of their votes
    @Query(value = "SELECT m.genres, m.primarytitle, r.numvotes, SUM(r.numvotes) AS totalNumVotes " +
            "FROM movies m INNER JOIN ratings r ON m.tconst = r.tconst " +
            "GROUP BY m.genres, m.primarytitle " +
            "ORDER BY m.genres, m.primarytitle", nativeQuery = true)
    List<Object[]> getMoviesGroupedByGenre();

    //    Query to update the runtimeMinutes in the database
    @Modifying
    @Transactional
    @Query(value = "UPDATE movies m SET m.runtimeminutes = m.runtimeminutes + CASE m.genres WHEN \"Documentary\" THEN 15 WHEN \"Animation\" THEN 30 ELSE 45 END", nativeQuery = true)
    void updateRuntimeMinutes();
}
