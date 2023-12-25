package com.roland.movietheater_jdbc.service.SeatService;

import com.roland.movietheater_jdbc.model.Seat;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToUpdateRoomInCinemaBranchException;

import java.util.List;

public interface ISeatService {



    List<Seat> getAllSeatsInRoomForAdmin(int cinemaId, int roomId) throws FailedToFindRoomInCinemaBranchException;

    Seat getSeatInRoomById(int cinemaId, int roomId, int seatId) throws FailedToFindSeatInCinemaBranchRoom;

    List<Seat> createSeatsInRoom(int cinemaId, int roomId, int roomCapacity) throws FailedToCreateSeatInCinemaBranchRoom, FailedToUpdateRoomInCinemaBranchException;


    List<Seat> getAllSeatsInRoomForUser(int cinemaId, int roomId);

    String deleteAllSeatsInRoom(int cinemaId, int roomId) throws FailedToDeleteSeatInCinemaBranchRoom, FailedToUpdateRoomInCinemaBranchException;
}
