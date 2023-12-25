package com.roland.movietheater_jdbc.repository.MovieEventRepository;

import com.roland.movietheater_jdbc.model.MovieEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MovieEventMapper implements RowMapper<MovieEvent> {

    @Override
    public MovieEvent mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieEvent movieEvent = new MovieEvent();

        movieEvent.setMovieId(resultSet.getInt("movie_id"));
        movieEvent.setRoomId(resultSet.getInt("room_id"));
        movieEvent.setMovieEventId(resultSet.getInt("movie_eventId"));
        movieEvent.setMovieStartTime(resultSet.getTimestamp("movie_start_time"));
        movieEvent.setMovieEndTime(resultSet.getTimestamp("movie_end_time"));
        movieEvent.setTicketPrice(resultSet.getDouble("ticket_price"));


        return movieEvent;
    }
}
