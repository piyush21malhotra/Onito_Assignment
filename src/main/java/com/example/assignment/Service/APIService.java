package com.example.assignment.Service;

import com.example.assignment.Model.MovieEntity;
import com.example.assignment.Model.RatingEntity;
import com.example.assignment.Repository.MovieRepository;
import com.example.assignment.Repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class APIService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RatingRepository ratingRepository;

    /* ApiService is responsible for the business logic/ the functionality for the API calls. */

    //  Function to get the longest duration movies from the database.
    public List<MovieEntity> getTop10Movie() {
        return movieRepository.getTop10Movies();
    }

    //    Function to save a new movie to the database
    public void addMovie(MovieEntity movie) {
        movieRepository.save(movie);
    }

    //    Function to save the rating for the new movie to the database
    public void addRating(RatingEntity rating) {
        ratingRepository.save(rating);
    }

    //    Function for extracting the Top rated movies in terms of averageRating > 6.0
    public List<Map<String, Object>> getTopRatedMovie() {

        List<Object[]> resultList = movieRepository.getTopRatedMovie();
        List<Map<String, Object>> movieList = new ArrayList<>();

        for(Object[] result : resultList) {
            String tconst = (String)result[0];
            String primaryTitle = (String)result[1];
            String genre = (String)result[2];
            Float averageRating = (Float)result[3];

            Map<String, Object> movie = new HashMap<>();
            movie.put("tconst", tconst);
            movie.put("primaryTitle", primaryTitle);
            movie.put("genres", genre);
            movie.put("averageRating", averageRating);

            movieList.add(movie);
        }
        return movieList;

        /* List<MovieRatingEntity> topRatedMovie = new ArrayList<>();
        List<String> movieGreaterThan6 = ratingRepository.getMovieGreaterThan6();
        List<String> movieTconst = new ArrayList<>();
        List<String> movieRating = new ArrayList<>();
        for(int i = 0; i<movieGreaterThan6.size(); i++) {
            String[] arr = movieGreaterThan6.get(i).split(",");
            movieTconst.add(i, arr[0]);
            movieRating.add(i, arr[1]);
        }
        List<MovieEntity> movies = movieRepository.getTopRatedMovie(movieTconst);

        for(int i = 0; i<movieGreaterThan6.size(); i++) {
            MovieRatingEntity movie = new MovieRatingEntity();
            movie.setTconst(movies.get(i).getTconst());
            movie.setPrimaryTitle(movies.get(i).getPrimaryTitle());
            movie.setGenres(movies.get(i).getGenres());
            movie.setAverageRating(Float.parseFloat(movieRating.get(i)));
            topRatedMovie.add(movie);
        } */
    }

    //    Function to get the movies by genre with the subtotal of their votes
    public List<Map<String, Object>> getMoviesByGenreWithNumVotes() {
        List<Object[]> resultList = movieRepository.getMoviesGroupedByGenre();
        List<Map<String, Object>> movieList = new ArrayList<>();
        String currentGenre = null;
        int totalNumVotesForGroup = 0;
        for (int i = 0; i < resultList.size(); i++) {
            Object[] result = resultList.get(i);
            String genre = (String) result[0];
            String title = (String) result[1];
            int numVotes = ((Number) result[2]).intValue();
            int totalNumVotes = ((Number) result[3]).intValue();
            Map<String, Object> movie = new HashMap<>();
            movie.put("genre", genre);
            movie.put("primaryTitle", title);
            movie.put("numVotes", numVotes);
            if (currentGenre == null || !currentGenre.equals(genre)) {
                if (i > 0) {
                    Map<String, Object> totalVotes = new HashMap<>();
                    totalVotes.put("genre", currentGenre);
                    totalVotes.put("primaryTitle", "Total Votes");
                    totalVotes.put("totalNumVotesForGroup", totalNumVotesForGroup);
                    movieList.add(totalVotes);
                }
                totalNumVotesForGroup = totalNumVotes;
                currentGenre = genre;
            } else {
                totalNumVotesForGroup += totalNumVotes;
            }
            movieList.add(movie);
            if (i == resultList.size() - 1) {
                Map<String, Object> totalVotes = new HashMap<>();
                totalVotes.put("genre", currentGenre);
                totalVotes.put("primaryTitle", "Total Votes");
                totalVotes.put("totalNumVotesForGroup", totalNumVotesForGroup);
                movieList.add(totalVotes);
            }
        }
        return movieList;
    }

    //    Function to update the runtimeMinutes in the database
    public void updateRuntimeMinutes() {
        movieRepository.updateRuntimeMinutes();
    }
}

