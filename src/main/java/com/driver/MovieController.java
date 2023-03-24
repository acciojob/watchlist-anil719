package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity<>("New Movie added Successfully", HttpStatus.OK);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("New Director Added Successfully",HttpStatus.OK);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movieName")String movieName,
                                                       @RequestParam("directorName")String directorName){
        movieService.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>("Added MovieDirectorPair successfully",HttpStatus.OK);
    }
    @GetMapping("movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name")String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable("name")String name){
        Director director = movieService.getDirectorByName(name);
        return new ResponseEntity<>(director,HttpStatus.CREATED);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMovieListByDirectorName(@PathVariable("directorName")String directorName){
        List<String> movieList = movieService.getMovieListByDirectorName(directorName);
        return new ResponseEntity<>(movieList, HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> getListOfAllMovies(){
        List<String> allMovies = movieService.getListOfAllMovies();
        return new ResponseEntity<>(allMovies, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("directorName")String directorName){
        movieService.deleteDirectorByName(directorName);
        return new ResponseEntity<>(directorName+" Deleted Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("Deleted All Directors Successfully", HttpStatus.CREATED);
    }
}
