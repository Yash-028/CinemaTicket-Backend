package com.roland.movietheater_jdbc.service.SeatService;

public class FailedToDeleteSeatInCinemaBranchRoom extends  Exception{
    public FailedToDeleteSeatInCinemaBranchRoom(int cinemaId, int roomId, int seatId) {
        super("Failed to delete seat: " + seatId + " In Room : " + roomId + " And Cinema Branch: " + cinemaId);
    }

    public FailedToDeleteSeatInCinemaBranchRoom(int cinemaId, int roomId) {
        super("Failed to delete seats In Room : " + roomId + " And Cinema Branch: " + cinemaId);
    }
}
