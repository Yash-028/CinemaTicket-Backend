package com.roland.movietheater_jdbc.repository.MovieEventRepository;


import com.roland.movietheater_jdbc.model.CineRoomMovieEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CineRoomMovieEventMapper implements RowMapper<CineRoomMovieEvent> {


    @Override
    public CineRoomMovieEvent mapRow(ResultSet resultSet, int i) throws SQLException {

        CineRoomMovieEvent cineRoomMovieEvent = new CineRoomMovieEvent();
        cineRoomMovieEvent.setMovieName(resultSet.getString("movie_name"));
        cineRoomMovieEvent.setMovieEventId(resultSet.getInt("movie_eventId"));
        cineRoomMovieEvent.setMovieId(resultSet.getInt("movie_id"));
        cineRoomMovieEvent.setRoomId(resultSet.getInt("room_id"));
        cineRoomMovieEvent.setMovieStartTime(resultSet.getTimestamp("movie_start_time"));
        cineRoomMovieEvent.setMovieEndTime(resultSet.getTimestamp("movie_end_time"));
        cineRoomMovieEvent.setTicketPrice(resultSet.getDouble("ticket_price"));

        return cineRoomMovieEvent;
    }
}
