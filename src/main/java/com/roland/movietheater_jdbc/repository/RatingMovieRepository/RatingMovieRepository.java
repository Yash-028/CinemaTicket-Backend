package com.roland.movietheater_jdbc.repository.RatingMovieRepository;

import com.roland.movietheater_jdbc.model.MovieRatingForm;
import com.roland.movietheater_jdbc.service.RatingMovieService.FailedToRateMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingMovieRepository implements IRatingMovieRepository {

    private static final String SQL_STATEMENT_TO_FIND_RATING_FOR_MOVIE = "SELECT movie_rating.* , customer.customer_username FROM movie_rating , customer WHERE movie_rating.customer_ratedId = customer.customer_id And movie_ratedId = ? ";

    private static final String SQL_STATEMENT_TO_DELETE_RATING_FOR_MOVIE = "DELETE FROM movie_rating WHERE customer_ratedId = ? AND movie_ratedId = ?";

    private static String SQL_STATEMENT_RATE_A_MOVIE_BY_CUSTOMER = "INSERT INTO movie_rating (customer_ratedId, movie_ratedId, movie_review_rating, movie_review_comment) VALUES (?,?,?,?)";

   private static String SQL_STATEMENT_TO_FIND_THE_AVG_RATING_FOR_MOVIE = "SELECT AVG(movie_review_rating) FROM movie_rating where movie_ratedId = ?";
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<MovieRatingForm> findAllRatingForMovie(int movieId) {

        List<MovieRatingForm> movieRatingFormList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_RATING_FOR_MOVIE, new MovieRatingFormMapper(), movieId);

        return movieRatingFormList;
    }

    @Override
    public String deleteMovieRatingForMovie(int movieId, int customerId) {
        jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_RATING_FOR_MOVIE, customerId, movieId);
        return "Rating of Customer id: " + customerId + " for movie id : " + movieId + " Has been deleted !";
    }

    @Override
    public MovieRatingForm createRatingForMovie(MovieRatingForm movieRatingForm) throws FailedToRateMovie {
        try {
            jdbcTemplate.update(SQL_STATEMENT_RATE_A_MOVIE_BY_CUSTOMER
                    , movieRatingForm.getCustomerId()
                    , movieRatingForm.getMovieId()
                    , movieRatingForm.getMovieReviewRating()
                    , movieRatingForm.getMovieReviewComment()
            );
            return movieRatingForm;
        } catch (Exception e) {
            throw new FailedToRateMovie("Failed To Rate Movie Please Try Again ! ( One Rating Per Movie )");
        }
    }

    @Override
    public double getAverageRatingForMovieForUser(int movieId)  {
        try {
            return  jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_THE_AVG_RATING_FOR_MOVIE,Double.class,movieId);
        } catch (Exception e) {
            return 0;
        }
    }


}
