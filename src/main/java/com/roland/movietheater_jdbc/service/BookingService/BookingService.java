package com.roland.movietheater_jdbc.service.BookingService;

import com.roland.movietheater_jdbc.model.*;
import com.roland.movietheater_jdbc.repository.BookingRepository.BookingRepository;
import com.roland.movietheater_jdbc.service.Customer.CustomerService;
import com.roland.movietheater_jdbc.service.Customer.FailedToFindAccountException;
import com.roland.movietheater_jdbc.service.SeatService.FailedToReserveSeatInCinemaBranch;
import com.roland.movietheater_jdbc.service.TicketService.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {

    private BookingRepository bookingRepository;
    private CustomerService customerService;
    private TicketService ticketService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, CustomerService customerService, TicketService ticketService) {
        this.bookingRepository = bookingRepository;
        this.customerService = customerService;
        this.ticketService = ticketService;
    }



    @Override
    public List<CineMovieEvent> getCinemaBranchHostingByMovieId(int movieId) {
        return bookingRepository.getCinemaBranchHostingByMovie(movieId);
    }

    @Override
    public List<CineMovieEventRoomTiming> getRoomTimingHostingMovieByMovieIdAndCinemaId(int movieId, int cinemaId) {
        return bookingRepository.getRoomTimingHostingMovieByMovieIdAndCinemaId(movieId, cinemaId);
    }

    @Override
    public List<CineMovieEventRoomSeat> getSeatAllSeatsForMovieEvent(int movieId, int cinemaId, int movieEvent, int roomId) {
        return bookingRepository.getSeatAllSeatsForMovieEvent(movieId, cinemaId,movieEvent ,roomId);
    }

    @Override
    public String reserveSeatForUser(int movieId ,int cinemaId, int movieEventId, int roomId, int seatId,int userId, double ticketPrice) throws FailedToFindAccountException, FailedToReserveSeatInCinemaBranch, FailedToReserveSeat {
        if (isUser(userId)) {
            int tickedIdGenerated = ticketService.createTicket(userId,ticketPrice);
            System.out.println(tickedIdGenerated);
            return bookingRepository.reserveSeatForUser(cinemaId,movieEventId, roomId, seatId, userId ,tickedIdGenerated);

        }

        else
            throw new FailedToFindAccountException("Not A User Please Log in Before Submitting");

    }

    @Override
    public List<Reservation> findReservationForCustomerById(int userId) {
        return bookingRepository.findReservationForCustomerById(userId);
    }


    private boolean isUser(int userID) throws FailedToFindAccountException {
        Customer customer = customerService.getCustomerById(userID);
            System.out.println(customer.getCustomerUsername());
            if (customer.getCustomerUsername().equals(null))
                return false;
            else
                return true;

    }
}
