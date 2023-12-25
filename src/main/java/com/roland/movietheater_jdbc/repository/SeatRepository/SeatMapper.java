package com.roland.movietheater_jdbc.repository.SeatRepository;

import com.roland.movietheater_jdbc.model.Seat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SeatMapper implements RowMapper<Seat> {

    @Override
    public Seat mapRow(ResultSet resultSet, int i) throws SQLException {
       Seat seat = new Seat();

       seat.setSeatId(resultSet.getInt("seat_id"));
       seat.setRoomId(resultSet.getInt("roomId_seat"));
       seat.setSeatRow(resultSet.getInt("seat_row"));
       seat.setSeatColumn(resultSet.getInt("seat_column"));


       return seat;

    }
}
