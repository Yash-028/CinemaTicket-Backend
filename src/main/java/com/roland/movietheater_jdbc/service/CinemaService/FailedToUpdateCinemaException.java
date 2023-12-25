package com.roland.movietheater_jdbc.service.CinemaService;

public class FailedToUpdateCinemaException extends Exception {
    public FailedToUpdateCinemaException(Throwable cause, int  cinemaId) {
        super("Failed to update Cinema: " + cinemaId, cause);
    }
}
