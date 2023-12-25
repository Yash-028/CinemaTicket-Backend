package com.roland.movietheater_jdbc.repository.SeatRepository;

import com.roland.movietheater_jdbc.model.Seat;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.SeatService.*;

import java.util.List;

public interface ISeatRepositoryDAO {


    List<Seat> getAllSeatsInRoomForAdmin(int cinemaId, int roomId) throws FailedToFindRoomInCinemaBranchException;

    List<Seat> getAllSeatsInRoomForUser(int cinemaId, int roomId);

    Seat getSeatInRoomById(int cinemaId, int roomId, int seatId) throws FailedToFindSeatInCinemaBranchRoom;

    Seat createSeatInRoom(int cinemaId, int roomId, Seat seat) throws FailedToCreateSeatInCinemaBranchRoom;

    String deleteAllSeatsInRoom(int cinemaId, int roomId) throws FailedToDeleteSeatInCinemaBranchRoom;
}
