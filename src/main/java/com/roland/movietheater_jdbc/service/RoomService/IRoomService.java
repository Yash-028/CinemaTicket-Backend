package com.roland.movietheater_jdbc.service.RoomService;

import com.roland.movietheater_jdbc.model.Room;
import com.roland.movietheater_jdbc.service.SeatService.FailedToCreateSeatInCinemaBranchRoom;

import java.util.List;

public interface IRoomService {

    List<Room> findAllRooms(int cinemaId);

    Room createRoomInBranch(Room room) throws FailedToInsertRoomInCinemaBranchException, FailedToCreateSeatInCinemaBranchRoom;

    int deleteRoomInBranch(int cinemaId, int roomId) throws FailedToDeleteRoomInCinemaBranchException, FailedToFindRoomInCinemaBranchException, FailedToInsertRoomInCinemaBranchException;

    Room updateRoomInBranch(int cinemaId, int roomId, Room room) throws FailedToUpdateRoomInCinemaBranchException;

    Room getRoomInCinemaBranchById(int cinemaId, int roomId) throws FailedToFindRoomInCinemaBranchException;
}
