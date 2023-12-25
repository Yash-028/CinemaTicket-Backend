package com.roland.movietheater_jdbc.service.RatingMovieService;

public class FailedToRateMovie extends Exception {
    public FailedToRateMovie(String message) {
        super(message);
    }
}
