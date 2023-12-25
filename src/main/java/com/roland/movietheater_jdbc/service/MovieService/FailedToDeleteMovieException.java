package com.roland.movietheater_jdbc.service.MovieService;

public class FailedToDeleteMovieException extends  Exception{
    public FailedToDeleteMovieException(Throwable cause, int movieId) {
        super("Failed to delete movie: " + movieId , cause);
    }
}
