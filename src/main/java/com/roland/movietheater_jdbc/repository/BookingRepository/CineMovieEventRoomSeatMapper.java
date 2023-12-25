package com.roland.movietheater_jdbc.repository.BookingRepository;

import com.roland.movietheater_jdbc.model.CineMovieEventRoomSeat;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CineMovieEventRoomSeatMapper implements RowMapper<CineMovieEventRoomSeat> {



    @Override
    public CineMovieEventRoomSeat mapRow(ResultSet resultSet, int i) throws SQLException {
        CineMovieEventRoomSeat cineMovieEventRoomSeat = new CineMovieEventRoomSeat();
        cineMovieEventRoomSeat.setCinemaId(resultSet.getInt("cinema_id"));
        cineMovieEventRoomSeat.setMovieEventId(resultSet.getInt("movie_eventID"));
        cineMovieEventRoomSeat.setBookingId(resultSet.getInt("booking_id"));
        cineMovieEventRoomSeat.setSeatId(resultSet.getInt("seat_id"));
        cineMovieEventRoomSeat.setRoomIdOfSeat(resultSet.getInt("roomId_seat"));
        cineMovieEventRoomSeat.setSeatRow(resultSet.getInt("seat_row"));
        cineMovieEventRoomSeat.setSeatColumn(resultSet.getInt("seat_column"));
        cineMovieEventRoomSeat.setTicketId(resultSet.getInt("ticket_id"));
        cineMovieEventRoomSeat.setSeatStatus(resultSet.getBoolean("seat_status"));
        cineMovieEventRoomSeat.setBookingDate(resultSet.getTimestamp("booking_date"));
        cineMovieEventRoomSeat.setTicketPrice(resultSet.getDouble("ticket_price"));
        return cineMovieEventRoomSeat;
    }
}
