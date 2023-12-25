package com.roland.movietheater_jdbc.repository.MovieRepository;

import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.service.MovieService.FailedToDeleteMovieException;
import com.roland.movietheater_jdbc.service.MovieService.FailedToFindMovieExcpetion;
import com.roland.movietheater_jdbc.service.MovieService.FailedToInsertMovieException;
import com.roland.movietheater_jdbc.service.MovieService.FailedToUpdateMovieException;

import java.util.List;


public interface IMovieRepositoryDAO {

    List<Movie> findAllMovies();

    Movie CreateMovieForBranch(Movie movie) throws FailedToInsertMovieException;

    int deleteMovieInBranch(int movieId) throws FailedToDeleteMovieException;

    Movie updateMovieInBranch(int movieId, Movie movie1) throws FailedToUpdateMovieException;

    Movie findMovieById(int movieId) throws FailedToFindMovieExcpetion;

    List<Movie> SearchForMovie(String search);
}
