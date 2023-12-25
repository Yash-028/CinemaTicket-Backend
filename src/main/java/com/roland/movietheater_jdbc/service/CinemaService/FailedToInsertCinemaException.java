package com.roland.movietheater_jdbc.service.CinemaService;

import com.roland.movietheater_jdbc.model.CinemaBranch;

public class FailedToInsertCinemaException extends Exception {
    public FailedToInsertCinemaException(Throwable cause, CinemaBranch cinemaBranch) {
        super("Failed to insert cinema: " + cinemaBranch.getCinemaId() +" Make sure you submitted correct data and cinemaID ", cause );
    }


}
