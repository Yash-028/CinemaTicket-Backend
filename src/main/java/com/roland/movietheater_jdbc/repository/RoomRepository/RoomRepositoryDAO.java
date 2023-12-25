package com.roland.movietheater_jdbc.repository.RoomRepository;

import com.roland.movietheater_jdbc.model.Room;
import com.roland.movietheater_jdbc.service.RoomService.FailedToDeleteRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToInsertRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.RoomService.FailedToUpdateRoomInCinemaBranchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomRepositoryDAO implements IRoomRepositoryDAO {


    private static final String SQL_STATEMENT_TO_FIND_ROOM_IN_CINEMA =
            "select * from room where cinema_branch = ?";

    private static final String SQL_STATEMENT_TO_CREATE_ROOM_IN_CINEMA =
            "insert into room (room_capacity,room_type,room_status,cinema_branch) values (?,?,?,?)";

    private static final String SQL_STATEMENT_TO_DELETE_ROOM_IN_CINEMA =
            "delete from room where room_id = ?";

    private static final String SQL_STATEMENT_TO_UPDATE_ROOM_IN_CINEMA_WHEN_ADDING_SEAT =
            "update room set room_status = 0 , cinema_branch =? where room_id = ? ";

    private static final String SQL_STATEMENT_TO_UPDATE_ROOM_IN_CINEMA_WHEN_DELETING_SEATS =
            "update room set room_status = 1 , cinema_branch =? where room_id = ? ";


    private static final String SQL_STATEMENT_TO_FIND_ROOM_IN_CINEMA_BRANCH_BY_ID =
            "select * from room where cinema_branch = ? and room_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Room> findAllRooms(int cinemaId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ROOM_IN_CINEMA, new RoomMapper(), cinemaId);


    }

    @Override
    public Room getRoomInCinemaBranch(int cinemaId, int roomId) throws FailedToFindRoomInCinemaBranchException {
        try {
            Room room = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_ROOM_IN_CINEMA_BRANCH_BY_ID, new RoomMapper(), cinemaId, roomId);
            return room;
        } catch (DataAccessException e) {
            throw new FailedToFindRoomInCinemaBranchException("Room not Found !");
        }
    }

    @Override
    public Room createRoomInBranch(Room room) throws FailedToInsertRoomInCinemaBranchException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_ROOM_IN_CINEMA,
                    room.getRoomCapacity(),
                    room.getRoomType(),
                    room.isRoomStatus(),
                    room.getCinemaId());


            return room;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            throw new FailedToInsertRoomInCinemaBranchException(e, room.getCinemaId(), room.getRoomId());
        }

    }

    @Override
    public int deleteRoomInBranch(int cinemaId, int roomId) throws FailedToDeleteRoomInCinemaBranchException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_ROOM_IN_CINEMA, roomId);
            return roomId;


        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            throw new FailedToDeleteRoomInCinemaBranchException(e, cinemaId, roomId);
        }
    }

    @Override
    public Room updateRoomInBranch(int cinemaId, int roomId, Room room) throws FailedToUpdateRoomInCinemaBranchException {
        try {

            if (!room.isRoomStatus()) {
                System.out.println("First loop");
                jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_ROOM_IN_CINEMA_WHEN_ADDING_SEAT,
                        cinemaId,
                        roomId);
            } else {
                System.out.println("Second loop");
                jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_ROOM_IN_CINEMA_WHEN_DELETING_SEATS,
                        cinemaId,
                        roomId);
            }


        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            throw new FailedToUpdateRoomInCinemaBranchException(e, cinemaId, roomId);
        }

        return room;
    }


}
