package com.roland.movietheater_jdbc.repository.BookingRepository;

import com.roland.movietheater_jdbc.model.CineMovieEvent;
import com.roland.movietheater_jdbc.model.CineMovieEventRoomSeat;
import com.roland.movietheater_jdbc.model.CineMovieEventRoomTiming;
import com.roland.movietheater_jdbc.model.Reservation;
import com.roland.movietheater_jdbc.service.BookingService.FailedToReserveSeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookingRepository implements IBookingRepository {

    private static final String SQL_STATEMENT_TO_FIND_CINEMA_BRANCH_HOSTING_MOVIE_BY_ID =
            "select C.cinema_id, ME.movie_id, C.cinema_name,  C.cinema_address, C.cinema_phone  from movie_event ME, room R, cinemabranch C " +
                    "where ME.room_id = R.room_id " +
                    " And C.cinema_id = R.cinema_branch And movie_id = ? Group by cinema_address;";

    private static final String SQL_STATEMENT_TO_FIND_ROOM_TIMING_HOSTING_MOVIE_BY_ID_IN_CINEMA_BRANCH =
            "select C.cinema_id, ME.* from movie_event ME, room R, cinemabranch C  where ME.room_id = R.room_id And C.cinema_id = R.cinema_branch And movie_id = ? And cinema_id = ? ";


    private static final String SQL_STATEMENT_TO_RESERVE_A_SEAT_FOR_A_MOVIE_EVENT = "insert into booking (ticket_id , seat_id ,movie_eventId ,seat_status) values (?,?,?,?)";


    private static final String SQL_STATEMENT_TO_FIND_All_SEATS_RESERVED_AND_NOT_RESERVED_IN_A_MOVIE_EVENT =
            " select Y.*, booking.booking_id  , booking.ticket_id , booking.seat_status , booking.booking_date" +
            " From(select C.cinema_id, ME.movie_eventId,ME.ticket_price, S .* from movie_event ME, room R, cinemabranch C, seat S" +
            " where ME.room_id=R.room_id  And C.cinema_id=R.cinema_branch " +
            " And S.roomId_seat=R.room_id" +
            " And movie_id=? And cinema_id= ? And ME.movie_eventId = ? And R.room_id= ? ) as Y left join booking on Y.seat_id =booking.seat_id and booking.movie_eventId = ? order by seat_id asc";


    private static final String SQL_STATEMENT_TO_FIND_BOOKING_FOR_CUSTOMERS_BY_ID = "SELECT * FROM reservation where customer_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<CineMovieEvent> getCinemaBranchHostingByMovie(int movieId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_CINEMA_BRANCH_HOSTING_MOVIE_BY_ID, new CineMovieEventMapper(), movieId);
    }

    @Override
    public List<CineMovieEventRoomTiming> getRoomTimingHostingMovieByMovieIdAndCinemaId(int movieId, int cinemaId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ROOM_TIMING_HOSTING_MOVIE_BY_ID_IN_CINEMA_BRANCH, new CineMovieEventRoomTimingMapper(), movieId, cinemaId);
    }

    @Override
    public List<CineMovieEventRoomSeat> getSeatAllSeatsForMovieEvent(int movieId, int cinemaId,int movieEvent, int roomId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_All_SEATS_RESERVED_AND_NOT_RESERVED_IN_A_MOVIE_EVENT, new CineMovieEventRoomSeatMapper(), movieId, cinemaId,movieEvent, roomId, movieEvent);
    }

    @Override
    public String reserveSeatForUser(int cinemaId,int movieEventId, int roomId, int seatId, int userID, int ticketId) throws FailedToReserveSeat {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_RESERVE_A_SEAT_FOR_A_MOVIE_EVENT, ticketId, seatId,movieEventId, 1);
            return "Your Seat Have Been Reserved!";
        } catch (DataAccessException e) {
            throw new FailedToReserveSeat("Could Not Reserve This Seat ! Try Again");
        }
    }

    @Override
    public List<Reservation> findReservationForCustomerById(int userId) {
        return jdbcTemplate.query(SQL_STATEMENT_TO_FIND_BOOKING_FOR_CUSTOMERS_BY_ID,new ReservationMapper(), userId);
    }


}
