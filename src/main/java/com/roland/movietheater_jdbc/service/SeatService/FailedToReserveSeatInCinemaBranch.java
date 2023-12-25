package com.roland.movietheater_jdbc.service.SeatService;

public class FailedToReserveSeatInCinemaBranch extends  Exception {
    public FailedToReserveSeatInCinemaBranch(int cinemaId, int roomId, int seatId) {
        super("Failed to reserve seat: " + seatId + " In Room : " + roomId + " And Cinema Branch: " + cinemaId);
    }

    public FailedToReserveSeatInCinemaBranch(String msg) {
        super(msg);
    }
}
