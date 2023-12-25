package com.roland.movietheater_jdbc.service.RatingMovieService;

import com.roland.movietheater_jdbc.model.Customer;
import com.roland.movietheater_jdbc.model.MovieRatingForm;
import com.roland.movietheater_jdbc.repository.RatingMovieRepository.RatingMovieRepository;
import com.roland.movietheater_jdbc.service.Customer.CustomerService;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingMovieService implements  IRatingMovieService{

    private RatingMovieRepository ratingMovieRepository;
    private CustomerService customerService;

    @Autowired
    public RatingMovieService(RatingMovieRepository ratingMovieRepository, CustomerService customerService){
        this.ratingMovieRepository = ratingMovieRepository;
        this.customerService = customerService;
    }

    @Override
    public List<MovieRatingForm> findAllRatingForMovie(int movieId) {
        return ratingMovieRepository.findAllRatingForMovie(movieId);
    }

    @Override
    public String deleteMovieRatingForMovie(int movieId, int customerId) {
        return ratingMovieRepository.deleteMovieRatingForMovie(movieId,customerId);
    }

    @Override
    public MovieRatingForm createRatingForMovie(MovieRatingForm movieRatingForm) throws FailedToRateMovie, FailedToFindAccountException {
        // Checking if customer exist
        Customer customer = customerService.getCustomerById(movieRatingForm.getCustomerId());
        System.out.println(customer.getCustomerUsername());
        return ratingMovieRepository.createRatingForMovie(movieRatingForm);
    }

    @Override
    public double getAverageRatingForMovieForUser(int movieId)  {
        return ratingMovieRepository.getAverageRatingForMovieForUser(movieId);
    }
}
