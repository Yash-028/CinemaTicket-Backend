package com.roland.movietheater_jdbc.service.CinemaService;


public class FailedToDeleteCinemaException extends Exception {
    public FailedToDeleteCinemaException(Throwable cause, int  cinemaId) {
        super("Failed to delete cinema: " + cinemaId, cause);
    }
}
