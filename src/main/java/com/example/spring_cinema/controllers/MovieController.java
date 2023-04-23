package com.example.spring_cinema.controllers;


import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    //get a list of movies
//    @GetMapping
//    public ResponseEntity<List<Movie>> getAllMovies(){
//        List<Movie> movies = movieService.getAllMovies();
//        return new ResponseEntity<>(movies, HttpStatus.OK);
//    }

    //get a single movie
    @GetMapping(value = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        Optional<Movie> movie = movieService.getMovieById(id);
        if(movie.isPresent()){
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMaxDurationMovies(@RequestParam(required = false) Integer maxDuration){
        List<Movie> movies;
        if (maxDuration == null){
            movies = movieService.getAllMovies();
        } else {
            movies = movieService.filterMoviesByDuration(maxDuration);
        }
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


    // save a movie
    @PostMapping
    public ResponseEntity<Movie> saveNewMovie(@RequestBody Movie movie){
        Movie savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    //update a movie
    @PutMapping(value = "/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        movie.setId(id);
        movieService.updateMovie(movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    //delete a movie
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return new ResponseEntity(null, HttpStatus.NO_CONTENT);
    }






}
