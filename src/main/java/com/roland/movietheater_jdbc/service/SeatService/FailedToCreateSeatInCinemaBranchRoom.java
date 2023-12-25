package com.roland.movietheater_jdbc.service.SeatService;

public class FailedToCreateSeatInCinemaBranchRoom extends  Exception{
    public FailedToCreateSeatInCinemaBranchRoom(int cinemaId, int roomId) {
        super("Failed to Creat seat In Room : " + roomId + " And Cinema Branch: " + cinemaId);
    }
}
