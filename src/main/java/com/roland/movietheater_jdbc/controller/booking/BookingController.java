package com.roland.movietheater_jdbc.controller.booking;

import com.roland.movietheater_jdbc.model.CineMovieEvent;
import com.roland.movietheater_jdbc.model.CineMovieEventRoomSeat;
import com.roland.movietheater_jdbc.model.CineMovieEventRoomTiming;
import com.roland.movietheater_jdbc.model.Reservation;
import com.roland.movietheater_jdbc.service.BookingService.BookingService;
import com.roland.movietheater_jdbc.service.BookingService.FailedToReserveSeat;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import com.roland.movietheater_jdbc.service.SeatService.FailedToReserveSeatInCinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookingController {

    public BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;

    }


    @GetMapping("/booking/movies/{movieId}")
    public ResponseEntity getCinemaBranchHostingMovieById(@PathVariable("movieId") int movieId) {
        List<CineMovieEvent> cineMovieEventList = bookingService.getCinemaBranchHostingByMovieId(movieId);
        List<CineMovieEventApiResponseForUser> responseList = buildCinemaBranchHostingByIdResponse(cineMovieEventList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);

    }

    @GetMapping("/booking/movies/{movieId}/cinemas/{cinemaId}")
    public ResponseEntity getRoomTimingHostingMovieByMovieIdAndCinemaId(@PathVariable("movieId") int movieId, @PathVariable("cinemaId") int cinemaId) {
        List<CineMovieEventRoomTiming> cineMovieEventRoomTimingList = bookingService.getRoomTimingHostingMovieByMovieIdAndCinemaId(movieId, cinemaId);
        List<CineMovieEventRoomTimingApiResponseForUser> responseList = buildRoomTimingForMovieEventInBranch(cineMovieEventRoomTimingList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/booking/movies/{movieId}/cinemas/{cinemaId}/movieEvents/{movieEvent}/rooms/{roomId}")
    public ResponseEntity getSeatAllSeatsForMovieEvent(@PathVariable("movieId") int movieId
            , @PathVariable("cinemaId") int cinemaId
            , @PathVariable("movieEvent") int movieEvent
            , @PathVariable("roomId") int roomId) {

        List<CineMovieEventRoomSeat> cineMovieEventRoomSeatList = bookingService.getSeatAllSeatsForMovieEvent(movieId, cinemaId, movieEvent, roomId);
        List<CineMovieEventRoomSeatApiResponseForUser> responseList = getSeatsAvailableForMovieEventApiResponse(cineMovieEventRoomSeatList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/bookings/{userId}")
    public ResponseEntity getAllBookingsForCustomerById(@PathVariable("userId") int userId) {
        System.out.println(userId);
        List<Reservation> reservationList = bookingService.findReservationForCustomerById(userId);
        List<ReservationApiResponseForUser> responseList = buildApiResponseForReservation(reservationList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @PostMapping("/booking/movies/{movieId}/cinemas/{cinemaId}/movieEvents/{movieEvent}/rooms/{roomId}/seats/{seatId}/user/{userId}/tickets/{ticketPrice}")
    public ResponseEntity reserveSeatForUser(@PathVariable("movieId") int movieId
            , @PathVariable("cinemaId") int cinemaId
            , @PathVariable("movieEvent") int movieEvent
            , @PathVariable("roomId") int roomId
            , @PathVariable("seatId") int seatId
            , @PathVariable("userId") int userId
            , @PathVariable("ticketPrice") double ticketPrice) {


        try {
            String seatReserved = bookingService.reserveSeatForUser(movieId, cinemaId, movieEvent, roomId, seatId, userId, ticketPrice);
            return ResponseEntity.status(HttpStatus.OK).body(seatReserved);
        } catch (FailedToFindAccountException | FailedToReserveSeatInCinemaBranch | FailedToReserveSeat e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getLocalizedMessage());
        }


    }





    private List<CineMovieEventRoomSeatApiResponseForUser> getSeatsAvailableForMovieEventApiResponse(List<CineMovieEventRoomSeat> cineMovieEventRoomSeatList) {
        List<CineMovieEventRoomSeatApiResponseForUser> responseList = new ArrayList<>();
        for (CineMovieEventRoomSeat cineMovieEventRoomSeat : cineMovieEventRoomSeatList) {
            responseList.add(getSeatsAvailableForMovieEvent(cineMovieEventRoomSeat));
        }

        return responseList;
    }

    private CineMovieEventRoomSeatApiResponseForUser getSeatsAvailableForMovieEvent(CineMovieEventRoomSeat cineMovieEventRoomSeat) {
        return new CineMovieEventRoomSeatApiResponseForUser().builder()
                .cinemaId(cineMovieEventRoomSeat.getCinemaId())
                .movieEventId(cineMovieEventRoomSeat.getMovieEventId())
                .roomIdOfSeat(cineMovieEventRoomSeat.getRoomIdOfSeat())
                .seatId(cineMovieEventRoomSeat.getSeatId())
                .bookingId(cineMovieEventRoomSeat.getBookingId())
                .ticketId(cineMovieEventRoomSeat.getTicketId())
                .seatRow(cineMovieEventRoomSeat.getSeatRow())
                .seatColumn(cineMovieEventRoomSeat.getSeatColumn())
                .seatStatus(cineMovieEventRoomSeat.isSeatStatus())
                .bookingDate(cineMovieEventRoomSeat.getBookingDate())
                .ticketPrice(cineMovieEventRoomSeat.getTicketPrice())
                .build();

    }


    private List<CineMovieEventRoomTimingApiResponseForUser> buildRoomTimingForMovieEventInBranch(List<CineMovieEventRoomTiming> cineMovieEventRoomTimingList) {
        List<CineMovieEventRoomTimingApiResponseForUser> responseList = new ArrayList<>();
        for (CineMovieEventRoomTiming cineMovieEventRoomTiming : cineMovieEventRoomTimingList) {
            responseList.add(getRoomTimingForMovieEventInBranch(cineMovieEventRoomTiming));
        }

        return responseList;
    }


    private CineMovieEventRoomTimingApiResponseForUser getRoomTimingForMovieEventInBranch(CineMovieEventRoomTiming cineMovieEventRoomTiming) {
        return new CineMovieEventRoomTimingApiResponseForUser().builder()
                .cinemaId(cineMovieEventRoomTiming.getCinemaId())
                .movieId(cineMovieEventRoomTiming.getMovieId())
                .room_id(cineMovieEventRoomTiming.getRoom_id())
                .movieEventId(cineMovieEventRoomTiming.getMovieEventId())
                .movieStartTime(cineMovieEventRoomTiming.getMovieStartTime())
                .movieEndTime(cineMovieEventRoomTiming.getMovieEndTime())
                .build();
    }


    private List<CineMovieEventApiResponseForUser> buildCinemaBranchHostingByIdResponse(List<CineMovieEvent> cineMovieEventList) {
        List<CineMovieEventApiResponseForUser> responseList = new ArrayList<>();
        for (CineMovieEvent cineMovieEvent : cineMovieEventList) {
            responseList.add(getCinemaBranchHostingByIdResponse(cineMovieEvent));
        }

        return responseList;
    }

    private CineMovieEventApiResponseForUser getCinemaBranchHostingByIdResponse(CineMovieEvent cineMovieEvent) {
        return new CineMovieEventApiResponseForUser().builder()
                .cinemaId(cineMovieEvent.getCinemaId())
                .movieId(cineMovieEvent.getMovieId())
                .cinemaName(cineMovieEvent.getCinemaName())
                .cinemaAddress(cineMovieEvent.getCinemaAddress())
                .cinemaPhone(cineMovieEvent.getCinemaPhone())
                .build();
    }

    private List<ReservationApiResponseForUser> buildApiResponseForReservation(List<Reservation> reservationList) {
        List<ReservationApiResponseForUser> responseList = new ArrayList<>();
        for (Reservation responseForUser : reservationList) {
            responseList.add(getReservationForUser(responseForUser));
        }

        return responseList;
    }

    private ReservationApiResponseForUser getReservationForUser(Reservation responseForUser) {
        return new ReservationApiResponseForUser().builder()
                .customerId(responseForUser.getCustomerId())
                .customerUsername(responseForUser.getCustomerUsername())
                .movieName(responseForUser.getMovieName())
                .cinemaAddress(responseForUser.getCinemaAddress())
                .roomName(responseForUser.getRoomName())
                .seatRow(responseForUser.getSeatRow())
                .bookingId(responseForUser.getBookingId())
                .movieStartTime(responseForUser.getMovieStartTime())
                .seatColumn(responseForUser.getSeatColumn())
                .ticketPrice(responseForUser.getTicketPrice())
                .build();
    }

}
