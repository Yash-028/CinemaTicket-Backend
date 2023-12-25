package com.roland.movietheater_jdbc.repository.CinemaRatingRepository;


import com.roland.movietheater_jdbc.model.CinemaRatingForm;
import com.roland.movietheater_jdbc.service.CinemaRatingService.FailedToRateCinemaBranch;

import java.util.List;

public interface ICinemaRatingRepositoryDAO {


    List<CinemaRatingForm> getAllRatingForCinemaBranchById(int cinemaId);

    CinemaRatingForm createRatingFormForCinemaBranch(CinemaRatingForm cinemaFormRating) throws FailedToRateCinemaBranch;

    String deleteRatingFormForCinemaBranchByCustomer(int cinemaId, int customerId, int cinemaRatingId);

    double getAverageRatingForCinemaBranchById(int cinemaId) throws FailedToRateCinemaBranch;
}
