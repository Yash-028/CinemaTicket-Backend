package com.roland.movietheater_jdbc.service.RoomService;

import com.roland.movietheater_jdbc.model.Room;
import com.roland.movietheater_jdbc.repository.RoomRepository.IRoomRepositoryDAO;
import com.roland.movietheater_jdbc.service.CinemaService.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService implements IRoomService {

    IRoomRepositoryDAO roomRepository;

    CinemaService cinemaService;

    @Autowired
    public RoomService(IRoomRepositoryDAO roomRepository, CinemaService cinemaService) {
        this.roomRepository = roomRepository;
        this.cinemaService = cinemaService;
    }

    @Override
    public List<Room> findAllRooms(int cinemaId) {
        return roomRepository.findAllRooms(cinemaId);
    }

    @Override
    public Room createRoomInBranch(Room room) throws FailedToInsertRoomInCinemaBranchException {
        cinemaService.updateCinemaCapacity(room.getRoomCapacity(),room.getCinemaId());
        return roomRepository.createRoomInBranch(room);

    }


    @Override
    public int deleteRoomInBranch(int cinemaId, int roomId) throws FailedToDeleteRoomInCinemaBranchException, FailedToFindRoomInCinemaBranchException, FailedToInsertRoomInCinemaBranchException {
        Room room = roomRepository.getRoomInCinemaBranch(cinemaId,roomId);
        cinemaService.updateCinemaCapacity(-room.getRoomCapacity(),cinemaId);
        int roomDeltedId = roomRepository.deleteRoomInBranch(cinemaId, roomId);
        return roomDeltedId;
    }

    @Override
    public Room updateRoomInBranch(int cinemaId, int roomId, Room room) throws FailedToUpdateRoomInCinemaBranchException {
        return roomRepository.updateRoomInBranch(cinemaId, roomId, room);
    }

    @Override
    public Room getRoomInCinemaBranchById(int cinemaId, int roomId) throws FailedToFindRoomInCinemaBranchException {
        return roomRepository.getRoomInCinemaBranch(cinemaId, roomId);
    }


}
