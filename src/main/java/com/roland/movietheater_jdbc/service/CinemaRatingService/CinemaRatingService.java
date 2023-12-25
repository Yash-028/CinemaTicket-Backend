package com.roland.movietheater_jdbc.service.CinemaRatingService;


import com.roland.movietheater_jdbc.model.CinemaRatingForm;
import com.roland.movietheater_jdbc.model.Customer;
import com.roland.movietheater_jdbc.repository.CinemaRatingRepository.CinemaRatingRepositoryDAO;
import com.roland.movietheater_jdbc.service.Customer.CustomerService;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaRatingService implements ICinemaRatingService {


    private CinemaRatingRepositoryDAO cinemaRatingRepositoryDAO;
    private CustomerService customerService;

    @Autowired
    public CinemaRatingService(CinemaRatingRepositoryDAO cinemaRatingRepositoryDAO, CustomerService customerService) {
        this.cinemaRatingRepositoryDAO = cinemaRatingRepositoryDAO;
        this.customerService = customerService;
    }

    @Override
    public List<CinemaRatingForm> getAllRatingForCinemaBranchById(int cinemaId) {
        return cinemaRatingRepositoryDAO.getAllRatingForCinemaBranchById(cinemaId);
    }

    @Override
    public CinemaRatingForm createRatingFormForCinemaBranch(CinemaRatingForm cinemaFormRating) throws FailedToFindAccountException, FailedToRateCinemaBranch {
        // Checking if customer exist
        Customer customer = customerService.getCustomerById(cinemaFormRating.getCustomerId());
        System.out.println(customer.getCustomerUsername());
        return cinemaRatingRepositoryDAO.createRatingFormForCinemaBranch(cinemaFormRating);
    }

    @Override
    public String deleteRatingFormForCinemaBranchByCustomer(int cinemaId, int customerId, int cinemaRatingId) {
        return cinemaRatingRepositoryDAO.deleteRatingFormForCinemaBranchByCustomer(cinemaId,customerId,cinemaRatingId);
    }

    @Override
    public double getAverageRatingForCinemaBranchById(int cinemaId) throws FailedToRateCinemaBranch {
        return cinemaRatingRepositoryDAO.getAverageRatingForCinemaBranchById(cinemaId);
    }
}
