package com.roland.movietheater_jdbc.service.CinemaService;

public class FailedToFindCinemaBranchException extends  Exception {
    public FailedToFindCinemaBranchException(String message) {
        super(message);
    }
}
