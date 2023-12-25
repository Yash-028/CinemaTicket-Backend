package com.roland.movietheater_jdbc.service.RatingMovieService;

import com.roland.movietheater_jdbc.model.MovieRatingForm;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;

import java.util.List;

public interface IRatingMovieService {

    List<MovieRatingForm> findAllRatingForMovie(int movieId);

    String deleteMovieRatingForMovie(int movieId, int customerId);

    MovieRatingForm createRatingForMovie(MovieRatingForm movieRatingForm) throws FailedToRateMovie, FailedToFindAccountException;

    double getAverageRatingForMovieForUser(int movieId) throws FailedToRateMovie;
}
