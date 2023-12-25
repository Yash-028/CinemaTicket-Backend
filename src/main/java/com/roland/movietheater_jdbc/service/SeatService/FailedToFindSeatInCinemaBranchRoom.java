package com.roland.movietheater_jdbc.service.SeatService;

public class FailedToFindSeatInCinemaBranchRoom extends  Exception {
    public FailedToFindSeatInCinemaBranchRoom(String message) {
        super(message);
    }
}
