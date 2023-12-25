package com.roland.movietheater_jdbc.service.MovieEventService;

public class FailedToUpdateMovieEventException extends Exception {
    public FailedToUpdateMovieEventException(Throwable e, int roomId, int movieId) {
        super("Failed to update movie event where movieId: " + movieId + "in roomId: " + roomId, e);

    }
}
