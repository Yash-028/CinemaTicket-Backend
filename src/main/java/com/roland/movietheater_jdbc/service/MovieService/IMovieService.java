package com.roland.movietheater_jdbc.service.MovieService;

import com.roland.movietheater_jdbc.model.Movie;

import java.util.List;

public interface IMovieService {

     List<Movie> findAllMovies();

     Movie createMovieForCinemaBranch( Movie movie) throws FailedToInsertMovieException;

     int deleteMovieInBranch( int movieId) throws FailedToDeleteMovieException;

     Movie findMovieById(int movieId) throws FailedToFindMovieExcpetion;

     Movie updateMovieInBranch( int  movieId, Movie movie) throws FailedToUpdateMovieException;

     List<Movie> SearchForMovie(String search);
}
