package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieHashMap;
    HashMap<String, Director> directorHashMap;
    HashMap<String, List<String>> movieDirectorHashMap;

    MovieRepository(){
        this.directorHashMap = new HashMap<>();
        this.movieHashMap = new HashMap<>();
        this.movieDirectorHashMap = new HashMap<>();
    }

    public void addMovie(Movie movie){
        String movieName = movie.getName();
        movieHashMap.put(movieName, movie);
    }

    public void addDirector(Director director){
        String directorName = director.getName();
        directorHashMap.put(directorName, director);
    }
    public void addMovieDirectorPair(String movieName,String directorName){
        List<String> movieList = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(directorName)){
            movieList = movieDirectorHashMap.get(directorName);
        }
        movieList.add(movieName);
        movieDirectorHashMap.put(directorName, movieList);
    }
    public Movie getMovieByName(String name){
        return movieHashMap.get(name);
    }
    public Director getDirectorByName(String name){
        return directorHashMap.get(name);
    }
    public List<String> getMovieListByDirectorName(String directorName){
        List<String> list = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(directorName))
            list = movieDirectorHashMap.get(directorName);
        return list;
    }

    public List<String> getListOfAllMovies(){
        return new ArrayList<>(movieHashMap.keySet());
    }

    public void deleteDirectorByName(String directorName){
        //Collect list of movies by the director
        List<String> movieList = new ArrayList<>();
        if(movieDirectorHashMap.containsKey(directorName)){
            movieList = movieDirectorHashMap.get(directorName);
        }
        //delete these  movies from movieMap
        for(String movieName : movieList){
            movieHashMap.remove(movieName);
        }

        //remove director From directorHashMap
        if(directorHashMap.containsKey(directorName)){
            directorHashMap.remove(directorName);
        }

        //remove director from movie Director HashMap
        if(movieDirectorHashMap.containsKey(directorName)){
            movieDirectorHashMap.remove(directorName);
        }
    }

    public void deleteAllDirectors(){
        HashSet<String> movieSet = new HashSet<>();

        directorHashMap = new HashMap<>();

        for(String director : movieDirectorHashMap.keySet()){
            movieSet.addAll(movieDirectorHashMap.get(director));
        }

        for(String movie : movieSet){
            movieHashMap.remove(movie);
        }

        movieDirectorHashMap = new HashMap<>();
    }
}
