package com.roland.movietheater_jdbc.service.MovieService;

public class FailedToUpdateMovieException extends  Exception{
    public FailedToUpdateMovieException(Throwable cause, int movieId) {
        super("Failed to update movie: " + movieId, cause);
    }
}
