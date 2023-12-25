package com.roland.movietheater_jdbc.service.MovieEventService;


public class FailedToDeleteMovieEventException extends Exception {
    public FailedToDeleteMovieEventException(Throwable e, int roomId, int movieId) {
        super("Failed to deleted movie event where movieId: " + movieId + "in roomId: " + roomId, e);
    }

}



