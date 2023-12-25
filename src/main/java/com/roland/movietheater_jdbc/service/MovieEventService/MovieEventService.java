package com.roland.movietheater_jdbc.service.MovieEventService;

import com.roland.movietheater_jdbc.model.CineRoomMovieEvent;
import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.model.MovieEvent;
import com.roland.movietheater_jdbc.repository.MovieEventRepository.IMovieEventRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieEventService implements IMovieEventService {

    @Autowired
    IMovieEventRepositoryDAO movieEventRepository;


    @Override
    public List<Movie> findAllMovieEvents() {
        return  movieEventRepository.findAllMovieEvents();
    }

    @Override
    public MovieEvent createMovieEvent(MovieEvent movieEvent) throws FailedToCreateMovieEventException {
        return movieEventRepository.createMovieEvent(movieEvent);
    }

    @Override
    public String deleteMovieEvent(int cinemaId, int roomId, int movieId, int movieEvent) throws FailedToDeleteMovieEventException {
        return movieEventRepository.deleteMovieEvent(cinemaId,roomId,movieId, movieEvent);
    }

    @Override
    public List<MovieEvent> getMovieEventTiming(int movieId) {
        return movieEventRepository.getMovieEventTiming(movieId);
    }

    @Override
    public List<CineRoomMovieEvent> getCineRoomMovieEvent(int cinemaId, int roomId) {
        return movieEventRepository.getCineRoomMovieEvent(cinemaId,roomId);
    }
}
