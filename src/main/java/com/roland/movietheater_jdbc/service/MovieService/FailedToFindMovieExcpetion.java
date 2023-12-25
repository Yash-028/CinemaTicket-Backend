package com.roland.movietheater_jdbc.service.MovieService;

public class FailedToFindMovieExcpetion extends  Exception{
    public FailedToFindMovieExcpetion(Throwable cause, int movieId) {
        super("Failed to find movie with the following Id: " + movieId , cause);
    }

}
