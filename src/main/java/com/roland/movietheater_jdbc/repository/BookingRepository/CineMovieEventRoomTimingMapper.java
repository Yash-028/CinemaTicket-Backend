package com.roland.movietheater_jdbc.repository.BookingRepository;

import com.roland.movietheater_jdbc.model.CineMovieEventRoomTiming;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CineMovieEventRoomTimingMapper implements RowMapper<CineMovieEventRoomTiming> {


    @Override
    public CineMovieEventRoomTiming mapRow(ResultSet resultSet, int i) throws SQLException {
        CineMovieEventRoomTiming cineMovieEventRoomTiming = new CineMovieEventRoomTiming();
        cineMovieEventRoomTiming.setCinemaId(resultSet.getInt("cinema_id"));
        cineMovieEventRoomTiming.setMovieId(resultSet.getInt("movie_id"));
        cineMovieEventRoomTiming.setRoom_id(resultSet.getInt("room_id"));
        cineMovieEventRoomTiming.setMovieEventId(resultSet.getInt("movie_eventId"));
        cineMovieEventRoomTiming.setMovieStartTime(resultSet.getTimestamp("movie_start_time"));
        cineMovieEventRoomTiming.setMovieEndTime(resultSet.getTimestamp("movie_end_time"));


        return cineMovieEventRoomTiming;
    }
}
