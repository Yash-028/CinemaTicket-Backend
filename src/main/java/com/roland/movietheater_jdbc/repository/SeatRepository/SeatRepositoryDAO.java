package com.roland.movietheater_jdbc.repository.SeatRepository;

import com.roland.movietheater_jdbc.model.Seat;
import com.roland.movietheater_jdbc.service.RoomService.FailedToFindRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.SeatService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeatRepositoryDAO implements ISeatRepositoryDAO {


    private static final String SQL_STATEMENT_TO_FIND_ALL_SEATS_IN_A_ROOM =
            "SELECT * FROM seat where roomId_seat = ?";

    private static final String SQL_STATEMENT_TO_FIND_ALL_SEATS_AVAILABLE_IN_A_ROOM =
            "SELECT * FROM seat where roomId_seat = ? ";


    private static final String SQL_STATEMENT_TO_FIND_SEAT_IN_ROOM_BY_ID =
            "SELECT * FROM seat where roomId_seat = ? and seat_id = ?";

    private static final String SQL_STATEMENT_TO_CREATE_A_SEAT_IN_A_ROOM =
            "INSERT INTO seat (roomId_seat,seat_row,seat_column) values (?,?,?)";


    private static final String SQL_STATEMENT_TO_DELETE_ALL_SEATS_IN_ROOM =
            "delete from seat where roomId_seat = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Seat> getAllSeatsInRoomForAdmin(int cinemaId, int roomId)  {


        List<Seat> seatList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ALL_SEATS_IN_A_ROOM
                , new SeatMapper()
                , roomId);

        return seatList;
    }


    @Override
    public Seat getSeatInRoomById(int cinemaId, int roomId, int seatId) throws FailedToFindSeatInCinemaBranchRoom {
        try {
            Seat seat = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_SEAT_IN_ROOM_BY_ID
                    , new SeatMapper()
                    , roomId
                    , seatId);
            return seat;
        } catch (Exception e) {
            throw new FailedToFindSeatInCinemaBranchRoom("Seat Not Found");
        }
    }


    @Override
    public List<Seat> getAllSeatsInRoomForUser(int cinemaId, int roomId) {
        List<Seat> seatList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ALL_SEATS_AVAILABLE_IN_A_ROOM
                , new SeatMapper()
                , roomId);

        return seatList;
    }


    @Override
    public Seat createSeatInRoom(int cinemaId, int roomId, Seat seat) throws FailedToCreateSeatInCinemaBranchRoom {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_A_SEAT_IN_A_ROOM,
                    roomId
                    , seat.getSeatRow()
                    , seat.getSeatColumn()
            );


            return seat;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            throw new FailedToCreateSeatInCinemaBranchRoom(cinemaId, roomId);
        }

    }



    @Override
    public String deleteAllSeatsInRoom(int cinemaId, int roomId) throws FailedToDeleteSeatInCinemaBranchRoom {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_ALL_SEATS_IN_ROOM
                    , roomId
                    );
            return "All Seats in Room: " + roomId + " From Branch: " + cinemaId  +" Has Been Removed";
        } catch (DataAccessException e) {
            throw new FailedToDeleteSeatInCinemaBranchRoom(cinemaId, roomId);
        }
    }








}
