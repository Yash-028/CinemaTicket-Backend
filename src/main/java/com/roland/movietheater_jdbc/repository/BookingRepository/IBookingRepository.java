package com.roland.movietheater_jdbc.repository.BookingRepository;

import com.roland.movietheater_jdbc.model.CineMovieEvent;
import com.roland.movietheater_jdbc.model.CineMovieEventRoomSeat;
import com.roland.movietheater_jdbc.model.CineMovieEventRoomTiming;
import com.roland.movietheater_jdbc.model.Reservation;
import com.roland.movietheater_jdbc.service.BookingService.FailedToReserveSeat;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IBookingRepository {


    List<CineMovieEvent> getCinemaBranchHostingByMovie(int movieId);

    List<CineMovieEventRoomTiming> getRoomTimingHostingMovieByMovieIdAndCinemaId(int movieId, int cinemaId);

    List<CineMovieEventRoomSeat> getSeatAllSeatsForMovieEvent(int movieId, int cinemaId,int movieEvent, int roomId);

    String reserveSeatForUser(int cinemaId,int movieEventId, int roomId, int seatId, int userID, int ticketId) throws FailedToReserveSeat;

    List<Reservation> findReservationForCustomerById(int userId);
}
