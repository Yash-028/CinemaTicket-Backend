package com.roland.movietheater_jdbc.service.RoomService;

public class FailedToUpdateRoomInCinemaBranchException extends  Exception{
    public FailedToUpdateRoomInCinemaBranchException(Throwable cause, int cinemaId, int movieId) {
        super("Failed to update room: " + movieId + " in CinemaBranch: " + cinemaId, cause);
    }
}
