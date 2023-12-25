package com.roland.movietheater_jdbc.service.MovieService;

public class FailedToInsertMovieException extends  Exception{
    public FailedToInsertMovieException(Throwable cause, int movieId) {
        super("Failed to insert movie: " + movieId, cause);
    }
}
