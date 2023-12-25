package com.roland.movietheater_jdbc.repository.RatingMovieRepository;

import com.roland.movietheater_jdbc.model.MovieRatingForm;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRatingFormMapper implements RowMapper<MovieRatingForm> {


    @Override
    public MovieRatingForm mapRow(ResultSet resultSet, int i) throws SQLException {
        MovieRatingForm movieRatingForm = new MovieRatingForm();
       movieRatingForm.setMovieId(resultSet.getInt("movie_ratedId"));
       movieRatingForm.setCustomerId(resultSet.getInt("customer_ratedId"));
       movieRatingForm.setMovieReviewComment(resultSet.getString("movie_review_comment"));
       movieRatingForm.setMovieReviewRating(resultSet.getDouble("movie_review_rating"));
       movieRatingForm.setCustomerUserName(resultSet.getString("customer_username"));

       return  movieRatingForm;
    }
}
