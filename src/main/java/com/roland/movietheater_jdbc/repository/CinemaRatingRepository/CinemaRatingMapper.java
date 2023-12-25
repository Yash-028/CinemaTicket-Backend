package com.roland.movietheater_jdbc.repository.CinemaRatingRepository;

import com.roland.movietheater_jdbc.model.CinemaRatingForm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaRatingMapper implements RowMapper<CinemaRatingForm> {

    @Override
    public CinemaRatingForm mapRow(ResultSet resultSet, int i) throws SQLException {
        CinemaRatingForm cinemaRatingForm = new CinemaRatingForm();
        cinemaRatingForm.setCinemaRatingFormId(resultSet.getInt("cinemaRating_id"));
        cinemaRatingForm.setCinemaId(resultSet.getInt("cinemaRated_id"));
        cinemaRatingForm.setCustomerId(resultSet.getInt("customerRater_id"));
        cinemaRatingForm.setRatingDate(resultSet.getTimestamp("date_of_rating"));
        cinemaRatingForm.setCustomerUsername(resultSet.getString("customer_username"));
        cinemaRatingForm.setCinemaReviewRating(resultSet.getDouble("cinema_review_Rating"));
        cinemaRatingForm.setCinemaRatingComment(resultSet.getString("cinema_rating_Review"));
        return cinemaRatingForm;
    }
}
