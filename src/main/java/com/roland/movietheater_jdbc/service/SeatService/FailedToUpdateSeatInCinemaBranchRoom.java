package com.roland.movietheater_jdbc.service.SeatService;

public class FailedToUpdateSeatInCinemaBranchRoom extends Exception {
    public FailedToUpdateSeatInCinemaBranchRoom(int cinemaId, int roomId, int seatId) {

        super("Failed to update seat: " + seatId + " In Room : " + roomId + " And Cinema Branch: " + cinemaId);

    }
}
