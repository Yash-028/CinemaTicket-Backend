package com.roland.movietheater_jdbc.controller.cinemaRating;

import com.roland.movietheater_jdbc.model.CinemaRatingForm;
import com.roland.movietheater_jdbc.service.CinemaRatingService.CinemaRatingService;
import com.roland.movietheater_jdbc.service.CinemaRatingService.FailedToRateCinemaBranch;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaRatingController {

    private CinemaRatingService cinemaRatingService;

    @Autowired
    public CinemaRatingController(CinemaRatingService cinemaRatingService) {
        this.cinemaRatingService = cinemaRatingService;
    }


    @GetMapping("/cinemas/{cinemaId}/cinemaRatings")
    public ResponseEntity getAllRatingForCinemaBranchById(@PathVariable("cinemaId") int cinemaId) {
        List<CinemaRatingForm> cinemaRatingFormList = cinemaRatingService.getAllRatingForCinemaBranchById(cinemaId);
        List<CinemaRatingApiResponseForAdminAndUser> responseList = buildApiResponseForCinemaBranchRating(cinemaRatingFormList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @GetMapping("/cinemas/{cinemaId}/cinemaAvgRating")
    public ResponseEntity getAverageRatingForCinemaBranchById(@PathVariable("cinemaId") int cinemaId) {
        try {
            double cinemaRatingAverage = cinemaRatingService.getAverageRatingForCinemaBranchById(cinemaId);
            return ResponseEntity.status(HttpStatus.OK).body(cinemaRatingAverage);
        } catch (FailedToRateCinemaBranch e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }


    @PostMapping("/cinemas/{cinemaId}/customers/{customerId}/cinemaRatings")
    public ResponseEntity createRatingFormForCinemaBranch(@PathVariable("cinemaId") int cinemaId,
                                                          @PathVariable("customerId") int customerId,
                                                          @RequestBody CinemaRatingApiRequestForUser request) {

        try {
            CinemaRatingForm cinemaRatingForm = cinemaRatingService.createRatingFormForCinemaBranch(getCinemaFormRating(cinemaId, customerId, request));
            CinemaRatingApiResponseForAdminAndUser response = getCinemaBranchRatingApiResponse(cinemaRatingForm);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindAccountException | FailedToRateCinemaBranch e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }

    }

    @DeleteMapping("/cinemas/{cinemaId}/customers/{customerId}/cinemaRatings/{cinemaRatingId}")
    public ResponseEntity deleteRatingFormForCinemaBranch(@PathVariable("cinemaId") int cinemaId,
                                                          @PathVariable("customerId") int customerId,
                                                          @PathVariable("cinemaRatingId") int cinemaRatingId) {

        String deletedCinemaRatingForm = cinemaRatingService.deleteRatingFormForCinemaBranchByCustomer(cinemaId, customerId, cinemaRatingId);

        return ResponseEntity.status(HttpStatus.OK).body(deletedCinemaRatingForm);

    }


    private CinemaRatingForm getCinemaFormRating(int cinemaId, int customerId, CinemaRatingApiRequestForUser request) {
        return new CinemaRatingForm().builder()
                .cinemaId(cinemaId)
                .customerId(customerId)
                .cinemaRatingComment(request.getCinemaRatingComment())
                .cinemaReviewRating(request.getCinemaReviewRating())
                .build();
    }


    private List<CinemaRatingApiResponseForAdminAndUser> buildApiResponseForCinemaBranchRating(List<CinemaRatingForm> cinemaRatingFormList) {
        List<CinemaRatingApiResponseForAdminAndUser> responseList = new ArrayList<>();
        for (CinemaRatingForm cinemaRatingForm : cinemaRatingFormList) {
            responseList.add(getCinemaBranchRatingApiResponse(cinemaRatingForm));
        }
        return responseList;

    }

    private CinemaRatingApiResponseForAdminAndUser getCinemaBranchRatingApiResponse(CinemaRatingForm cinemaRatingForm) {
        return new CinemaRatingApiResponseForAdminAndUser().builder()
                .cinemaId(cinemaRatingForm.getCinemaId())
                .cinemaRatingFormId(cinemaRatingForm.getCinemaRatingFormId())
                .customerId(cinemaRatingForm.getCustomerId())
                .cinemaReviewRating(cinemaRatingForm.getCinemaReviewRating())
                .ratingDate(cinemaRatingForm.getRatingDate())
                .cinemaRatingComment(cinemaRatingForm.getCinemaRatingComment())
                .CustomerUsername(cinemaRatingForm.getCustomerUsername())
                .build();
    }

}
