package com.roland.movietheater_jdbc.service.MovieEventService;

public class FailedToCreateMovieEventException extends Exception {
    public FailedToCreateMovieEventException(Throwable e, int roomId, int movieId) {
        super("Failed to show movie event where movieId: " + movieId + "in roomId: " + roomId, e);

    }
}
