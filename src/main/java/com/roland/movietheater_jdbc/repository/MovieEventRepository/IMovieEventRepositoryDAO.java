package com.roland.movietheater_jdbc.repository.MovieEventRepository;

import com.roland.movietheater_jdbc.model.CineRoomMovieEvent;
import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.model.MovieEvent;
import com.roland.movietheater_jdbc.service.MovieEventService.FailedToCreateMovieEventException;
import com.roland.movietheater_jdbc.service.MovieEventService.FailedToDeleteMovieEventException;

import java.util.List;

public interface IMovieEventRepositoryDAO {


    List<Movie> findAllMovieEvents();

    MovieEvent createMovieEvent(MovieEvent movieEvent) throws FailedToCreateMovieEventException;

    String deleteMovieEvent(int cinemaId, int roomId, int movieId, int movieEventId) throws FailedToDeleteMovieEventException;

    List<MovieEvent> getMovieEventTiming(int roomId);


    List<CineRoomMovieEvent> getCineRoomMovieEvent(int cinemaId, int roomId);
}
