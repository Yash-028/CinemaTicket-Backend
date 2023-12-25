package com.roland.movietheater_jdbc.repository.BookingRepository;


import com.roland.movietheater_jdbc.model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
       Reservation reservation = new Reservation();
       reservation.setCustomerId(resultSet.getInt("customer_id"));
       reservation.setCustomerUsername(resultSet.getString("customer_username"));
       reservation.setMovieName(resultSet.getString("movie_name"));
       reservation.setCinemaAddress(resultSet.getString("cinema_address"));
       reservation.setRoomName(resultSet.getString("room_type"));
       reservation.setSeatRow(resultSet.getInt("seat_row"));
       reservation.setSeatColumn(resultSet.getInt("seat_column"));
       reservation.setBookingId(resultSet.getInt("booking_id"));
       reservation.setMovieStartTime(resultSet.getTimestamp("movie_start_time"));
       reservation.setTicketPrice(resultSet.getDouble("ticket_price"));

       return reservation;
    }
}
