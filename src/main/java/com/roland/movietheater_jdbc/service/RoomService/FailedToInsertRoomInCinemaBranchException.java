package com.roland.movietheater_jdbc.service.RoomService;

public class FailedToInsertRoomInCinemaBranchException extends  Exception{
    public FailedToInsertRoomInCinemaBranchException(Throwable cause, int cinemaId, int movieId) {
        super("Failed to insert room: " + movieId + " in CinemaBranch: " + cinemaId, cause);
    }

    public FailedToInsertRoomInCinemaBranchException(String msg) {
        super(msg);
    }
}
