package com.example.spring_cinema.services;

import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    //Get all movies
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    //get a movie by id
    public Optional<Movie> getMovieById(Long id){
        return movieRepository.findById(id);
    }

    // save a movie
    public Movie saveMovie(Movie movie){
        movieRepository.save(movie);
        return movie;
    }


    //Update a movie in the database
    public Movie updateMovie(Movie movie){
        movieRepository.save(movie);
        return movie;
    }


    //Delete a movie from the database
    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }


    //Use a @RequestParam to add a maxDuration parameter to the GET /movies request. Pass this through to the service and use it to return only the movies with a duration less than the value.
    public List<Movie> filterMoviesByDuration(int maxDuration){
        List<Movie> allMovies = movieRepository.findAll();
        List<Movie> filteredMovies = new ArrayList<>();

        for (Movie movie : allMovies) {
            if (movie.getDuration() <= maxDuration){
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }


}
