package com.example.assignment.Controller;

import com.example.assignment.Model.MovieEntity;
import com.example.assignment.Model.RatingEntity;
import com.example.assignment.Service.APIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1")
public class APIController {

    @Autowired
    APIService apiService;

    /* Api Controller holds all the api related matter, it will contain all the api methods. */

    //    GetApi for getting the longest duration movies
    @GetMapping("/longest-duration-movies")
    public ResponseEntity<List<MovieEntity>> getTop10Movie() {
        List<MovieEntity> top10Movies = apiService.getTop10Movie();
        return new ResponseEntity<> (top10Movies, HttpStatus.ACCEPTED);
    }

    //    PostApi for saving a new movie
    @PostMapping("new-movie")
    public ResponseEntity<String> saveMovie(@RequestBody() MovieEntity movie) {
        apiService.addMovie(movie);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    //    PostApi for saving the rating for the new movie
    @PostMapping("new-rating")
    public ResponseEntity<String> saveRating(@RequestBody() RatingEntity rating) {
        apiService.addRating(rating);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    //    GetApi for getting the Top rated movies in terms of averageRating > 6.0
    @GetMapping("/top-rated-movies")
    public ResponseEntity<List<Map<String, Object>>> getTopRatedMovie() {
        List<Map<String, Object>> top10Movies = apiService.getTopRatedMovie();
        return new ResponseEntity<> (top10Movies, HttpStatus.ACCEPTED);
    }

    //    GetApi for extracting the genres with their subtotals of votes
    @GetMapping("/genre-movies-with-subtotals")
    public List<Map<String, Object>> getMoviesByGenreWithNumVotes() {
        return apiService.getMoviesByGenreWithNumVotes();
    }

    //    PostApi to update the runtimeMinutes with conditions as
    //    15 if genre = Documentary
    //    30 if genre = Animation
    //    45 for the rest
    @PostMapping("/update-runtime-minutes")
    public ResponseEntity<String> updateRuntimeMinutes() {
        apiService.updateRuntimeMinutes();
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}

