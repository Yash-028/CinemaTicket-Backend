package com.roland.movietheater_jdbc.controller.seat;

import com.roland.movietheater_jdbc.model.Seat;
import com.roland.movietheater_jdbc.service.RoomService.FailedToUpdateRoomInCinemaBranchException;
import com.roland.movietheater_jdbc.service.SeatService.FailedToCreateSeatInCinemaBranchRoom;
import com.roland.movietheater_jdbc.service.SeatService.FailedToDeleteSeatInCinemaBranchRoom;
import com.roland.movietheater_jdbc.service.SeatService.FailedToFindSeatInCinemaBranchRoom;
import com.roland.movietheater_jdbc.service.SeatService.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatController {

    private SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/admin/cinemas/{cinemaId}/rooms/{roomId}/seats")
    public ResponseEntity getAllSeatsInRoomForAdmin(@PathVariable("cinemaId") int cinemaId,
                                                    @PathVariable("roomId") int roomId) {

        List<Seat> seatList = seatService.getAllSeatsInRoomForAdmin(cinemaId, roomId);
        List<SeatApiResponseForAdmin> responseList = buildSeatListResponseForAdmin(seatList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @GetMapping("cinemas/{cinemaId}/rooms/{roomId}/seats")
    public ResponseEntity getAllSeatsInRoomForUser(@PathVariable("cinemaId") int cinemaId,
                                                   @PathVariable("roomId") int roomId) {

        List<Seat> seatList = seatService.getAllSeatsInRoomForUser(cinemaId, roomId);
        List<SeatApiResponseForAdmin> responseList = buildSeatListResponseForAdmin(seatList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @GetMapping("/admin/cinemas/{cinemaId}/rooms/{roomId}/seats/{seatId}")
    public ResponseEntity getSeatInRoomById(@PathVariable("cinemaId") int cinemaId,
                                            @PathVariable("roomId") int roomId,
                                            @PathVariable("seatId") int seatId) {

        try {
            Seat seat = seatService.getSeatInRoomById(cinemaId, roomId, seatId);
            SeatApiResponseForAdmin response = getSeatApiResponseForAdmin(seat);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToFindSeatInCinemaBranchRoom e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }

    }


    @DeleteMapping("/admin/cinemas/{cinemaId}/rooms/{roomId}/seats")
    public ResponseEntity deleteAllSeatsInRoom(@PathVariable("cinemaId") int cinemaId,
                                               @PathVariable("roomId") int roomId) {

        try {
            String seatIdDeleted = seatService.deleteAllSeatsInRoom(cinemaId, roomId);
            return ResponseEntity.status(HttpStatus.OK).body(seatIdDeleted);
        } catch (FailedToDeleteSeatInCinemaBranchRoom | FailedToUpdateRoomInCinemaBranchException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }
    }


    @PostMapping("/admin/cinemas/{cinemaId}/rooms/{roomId}/{roomCapacity}/seats")
    public ResponseEntity createSeatsInRoom(@PathVariable("cinemaId") int cinemaId,
                                            @PathVariable("roomId") int roomId,
                                            @PathVariable("roomCapacity") int roomCapacity
    ) {

        try {
            List<Seat> seatList = seatService.createSeatsInRoom(cinemaId, roomId, roomCapacity);
            List<SeatApiResponseForAdmin> responseList = buildSeatListResponseForAdmin(seatList);
            return ResponseEntity.status(HttpStatus.OK).body(responseList);
        } catch (FailedToCreateSeatInCinemaBranchRoom | FailedToUpdateRoomInCinemaBranchException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getLocalizedMessage());
        }

    }


    private List<SeatApiResponseForAdmin> buildSeatListResponseForAdmin(List<Seat> seatList) {
        List<SeatApiResponseForAdmin> responseList = new ArrayList<>();
        for (Seat seat : seatList) {
            responseList.add(getSeatApiResponseForAdmin(seat));
        }

        return responseList;
    }

    private SeatApiResponseForAdmin getSeatApiResponseForAdmin(Seat seat) {
        return new SeatApiResponseForAdmin().builder()
                .roomId(seat.getRoomId())
                .seatId(seat.getSeatId())
                .seatRow(seat.getSeatRow())
                .seatColumn(seat.getSeatColumn())
                .build();


    }




}
