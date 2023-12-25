package com.roland.movietheater_jdbc.service.RoomService;

public class FailedToFindRoomInCinemaBranchException extends  Exception {
    public FailedToFindRoomInCinemaBranchException(String message) {
        super(message);
    }
}
