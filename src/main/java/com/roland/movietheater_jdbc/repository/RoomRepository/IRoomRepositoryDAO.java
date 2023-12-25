package com.roland.movietheater_jdbc.repository.RoomRepository;

import com.roland.movietheater_jdbc.model.Room;
import com.roland.movietheater_jdbc.service.RoomService.FailedToDeleteRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToInsertRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToUpdateRoomInCinemaBranchException;

import java.util.List;

public interface IRoomRepositoryDAO {


    List<Room> findAllRooms(int cinemaId);

    Room createRoomInBranch(Room room) throws FailedToInsertRoomInCinemaBranchException;

    int deleteRoomInBranch(int cinemaId, int roomId) throws FailedToDeleteRoomInCinemaBranchException;

    Room updateRoomInBranch(int cinemaId, int roomId, Room room) throws FailedToUpdateRoomInCinemaBranchException;

    Room getRoomInCinemaBranch(int cinemaId, int roomId) throws FailedToFindRoomInCinemaBranchException;
}
