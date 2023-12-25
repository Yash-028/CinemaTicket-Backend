package com.roland.movietheater_jdbc.controller.cinema;

import com.roland.movietheater_jdbc.model.CinemaBranch;
import com.roland.movietheater_jdbc.service.CinemaService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {


    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    @GetMapping("/admin/cinemas")
    public ResponseEntity<List<CinemaBranchApiResponseForAdmin>> getCinemaBranchForAdmin() {
        List<CinemaBranch> cinemaBranches = cinemaService.findAllCinemaBranch();
        List<CinemaBranchApiResponseForAdmin> responseList = buildResponseForAdmin(cinemaBranches);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @GetMapping("/cinemas")
    public ResponseEntity<List<CinemaBranchApiResponseForUser>> getCinemaBranchForUser() {
        List<CinemaBranch> cinemaBranches = cinemaService.findAllCinemaBranch();
        List<CinemaBranchApiResponseForUser> responseList = buildResponsListForUser(cinemaBranches);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/cinemas/{cinemaId}")
    public ResponseEntity getCinemaBranchForUserById(@PathVariable("cinemaId") int cinemaId) {
        try {
            CinemaBranch cinemaBranch = cinemaService.getCinemaBranchForUserById(cinemaId);
            CinemaBranchApiResponseForUser response = getResponseForUser(cinemaBranch);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindCinemaBranchException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }


    @PostMapping("/admin/cinemas")
    public ResponseEntity createCinemaBranch(@RequestBody CinemaBranchApiRequestForAdmin request) {
        try {
            CinemaBranch cinemaBranch = cinemaService.createCinemaBranch(getCinemaBranch(request));
            CinemaBranchApiResponseForAdmin response = getCinemaBranchApiResponse(cinemaBranch);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (FailedToInsertCinemaException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @DeleteMapping("/admin/cinemas/{cinemaId}")
    public ResponseEntity deleteCinemaBranch(@PathVariable("cinemaId") int cinemaId) {
        try {
            int cinemaIdDeleted = cinemaService.deleteCinemaBranch(cinemaId);
            return ResponseEntity.status(HttpStatus.OK).body(cinemaIdDeleted);
        } catch (FailedToDeleteCinemaException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/admin/cinemas/{cinemaId}")
    public ResponseEntity updateCinemaBranch(@PathVariable("cinemaId") int cinemaId, @RequestBody CinemaBranchApiRequestForAdmin request) {
        try {
            CinemaBranch cinemaBranch = cinemaService.updateCinemaBranch(cinemaId, getCinemaBranch(request));
            CinemaBranchApiResponseForAdmin response = getCinemaBranchApiResponse(cinemaBranch);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToUpdateCinemaException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    private CinemaBranch getCinemaBranch(CinemaBranchApiRequestForAdmin request) {
        return CinemaBranch.builder()

                .cinemaName(request.getCinemaName())
                .cinemaAddress(request.getCinemaAddress())
                .cinemaPhone(request.getCinemaPhone())
                .cinemaManager(request.getCinemaManager())
                .cinemaSeatCapacity(request.getCinemaSeatCapacity())
                .build();
    }


    private List<CinemaBranchApiResponseForUser> buildResponsListForUser(List<CinemaBranch> cinemaBranches) {
        List<CinemaBranchApiResponseForUser> responseList = new ArrayList<>();
        for (CinemaBranch cinemaBranch : cinemaBranches) {
            responseList.add(getResponseForUser(cinemaBranch));
        }
        return responseList;
    }

    CinemaBranchApiResponseForUser getResponseForUser(CinemaBranch cinemaBranch) {
        return new CinemaBranchApiResponseForUser().builder()
                .cinemaId(cinemaBranch.getCinemaId())
                .cinemaName(cinemaBranch.getCinemaName())
                .cinemaAddress(cinemaBranch.getCinemaAddress())
                .cinemaPhone(cinemaBranch.getCinemaPhone())
                .build();

    }


    private List<CinemaBranchApiResponseForAdmin> buildResponseForAdmin(List<CinemaBranch> cinemaBranches) {
        List<CinemaBranchApiResponseForAdmin> responseList = new ArrayList<>();
        for (CinemaBranch cinemaBranch : cinemaBranches) {
            responseList.add(getCinemaBranchApiResponse(cinemaBranch));
        }
        return responseList;

    }

    private CinemaBranchApiResponseForAdmin getCinemaBranchApiResponse(CinemaBranch cinemaBranch) {
        return new CinemaBranchApiResponseForAdmin().builder()
                .cinemaId(cinemaBranch.getCinemaId())
                .cinemaName(cinemaBranch.getCinemaName())
                .cinemaAddress(cinemaBranch.getCinemaAddress())
                .cinemaPhone(cinemaBranch.getCinemaPhone())
                .cinemaManager(cinemaBranch.getCinemaManager())
                .cinemaSeatCapacity(cinemaBranch.getCinemaSeatCapacity())
                .build();
    }


}
