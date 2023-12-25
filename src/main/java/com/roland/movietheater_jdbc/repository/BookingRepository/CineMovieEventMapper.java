package com.roland.movietheater_jdbc.repository.BookingRepository;

import com.roland.movietheater_jdbc.model.CineMovieEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CineMovieEventMapper implements RowMapper<CineMovieEvent> {
    @Override
    public CineMovieEvent mapRow(ResultSet resultSet, int i) throws SQLException {
        CineMovieEvent cineMovieEvent = new CineMovieEvent();

        cineMovieEvent.setCinemaId(resultSet.getInt("cinema_id"));
        cineMovieEvent.setMovieId(resultSet.getInt("movie_id"));
        cineMovieEvent.setCinemaName(resultSet.getString("cinema_name"));
        cineMovieEvent.setCinemaAddress(resultSet.getString("cinema_address"));
        cineMovieEvent.setCinemaPhone(resultSet.getString("cinema_phone"));


        return cineMovieEvent;
    }
}
