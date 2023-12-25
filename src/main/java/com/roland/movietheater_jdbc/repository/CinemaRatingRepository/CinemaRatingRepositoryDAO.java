package com.roland.movietheater_jdbc.repository.CinemaRatingRepository;

import com.roland.movietheater_jdbc.model.CinemaRatingForm;
import com.roland.movietheater_jdbc.service.CinemaRatingService.FailedToRateCinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CinemaRatingRepositoryDAO implements ICinemaRatingRepositoryDAO {


    private static final String SQL_STATEMENT_TO_FIND_RATING_FOR_CINEMA_BRANCH_BY_ID =
            "SELECT C.customer_username  ,CR.* FROM cinema_rating CR,  customer C where CR.customerRater_id = C.customer_id AND cinemaRated_id =?;";

    private static final String SQL_STATEMENT_TO_CREATE_RATING_FOR_CINEMA_BRANCH =
            "INSERT INTO cinema_rating (cinemaRated_id, customerRater_id, cinema_review_Rating, cinema_rating_Review) VALUES (?,?,?,?)";

    private static final String SQL_STATEMENT_TO_DELETE_RATING_FOR_CINEMABRANCH =
            "DELETE FROM cinema_rating where cinemaRating_id = ? AND cinemaRated_id = ? AND customerRater_id = ? ";

    private static final String SQL_STATEMENT_TO_FIND_AVG_RATING_FOR_CINEMA = "SELECT AVG(cinema_review_Rating) FROM cinema_rating where cinemaRated_id =?";


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<CinemaRatingForm> getAllRatingForCinemaBranchById(int cinemaId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_RATING_FOR_CINEMA_BRANCH_BY_ID, new CinemaRatingMapper(), cinemaId);
    }

    @Override
    public CinemaRatingForm createRatingFormForCinemaBranch(CinemaRatingForm cinemaFormRating) throws FailedToRateCinemaBranch {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_RATING_FOR_CINEMA_BRANCH
                    , cinemaFormRating.getCinemaId()
                    , cinemaFormRating.getCustomerId()
                    , cinemaFormRating.getCinemaReviewRating()
                    , cinemaFormRating.getCinemaRatingComment());

            return cinemaFormRating;
        } catch (DataAccessException e) {
            throw new FailedToRateCinemaBranch("Failed To Create Rating For Cinema! ");
        }
    }

    @Override
    public String deleteRatingFormForCinemaBranchByCustomer(int cinemaId, int customerId, int cinemaRatingId) {
        jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_RATING_FOR_CINEMABRANCH, cinemaRatingId, cinemaId, customerId);
        return "Rating Form Have Been Deleted !";
    }

    @Override
    public double getAverageRatingForCinemaBranchById(int cinemaId){

        try {
            Double ratingAvg = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_FIND_AVG_RATING_FOR_CINEMA, Double.class, cinemaId);
            return ratingAvg;
        } catch (Exception e) {
            return 0;
        }


    }
}
