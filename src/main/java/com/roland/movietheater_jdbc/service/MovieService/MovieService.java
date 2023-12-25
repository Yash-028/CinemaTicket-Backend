package com.roland.movietheater_jdbc.service.MovieService;

import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.repository.MovieRepository.IMovieRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements IMovieService {

    @Autowired
    private IMovieRepositoryDAO movieRepository;

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public Movie createMovieForCinemaBranch(Movie movie) throws FailedToInsertMovieException {
        return movieRepository.CreateMovieForBranch(movie);
    }

    @Override
    public int deleteMovieInBranch(int movieId) throws FailedToDeleteMovieException {
        return movieRepository.deleteMovieInBranch(movieId);
    }

    @Override
    public Movie findMovieById(int movieId) throws FailedToFindMovieExcpetion {
        return  movieRepository.findMovieById(movieId);
    }

    public Movie updateMovieInBranch(int movieId, Movie movie) throws FailedToUpdateMovieException {
        return movieRepository.updateMovieInBranch(movieId, movie);
    }

    @Override
    public List<Movie> SearchForMovie(String search) {
        return movieRepository.SearchForMovie(search);
    }
}
