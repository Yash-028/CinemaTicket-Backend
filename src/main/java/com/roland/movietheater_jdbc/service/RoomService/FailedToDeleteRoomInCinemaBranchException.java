package com.roland.movietheater_jdbc.service.RoomService;

public class FailedToDeleteRoomInCinemaBranchException extends  Exception{
    public FailedToDeleteRoomInCinemaBranchException(Throwable cause, int cinemaId, int movieId) {
        super("Failed to delete room: " + movieId + " in CinemaBranch: " + cinemaId, cause);
    }
}
