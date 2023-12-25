package com.roland.movietheater_jdbc.repository.TicketRepository;

import com.roland.movietheater_jdbc.service.SeatService.FailedToReserveSeatInCinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepositoryDAO implements ITicketRepositoryDAO {

    private static final String SQL_STATEMENT_TO_CREATE_A_TICKET = "INSERT INTO ticket (customer_id, ticket_price) VALUES (?,?)";

    private static final String SQL_STATEMENT_TO_RETURN_THE_TICKET_ID_OF_LAST_RESERVATION
            = "select max(ticket_id) from ticket";


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public int createTicket(int userID, double ticketPrice) throws FailedToReserveSeatInCinemaBranch {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_A_TICKET, userID, ticketPrice);
            int tickedIdGenerated = jdbcTemplate.queryForObject(SQL_STATEMENT_TO_RETURN_THE_TICKET_ID_OF_LAST_RESERVATION, Integer.class);
            return tickedIdGenerated;
        } catch (DataAccessException e) {
            throw new FailedToReserveSeatInCinemaBranch("Failed To Reserve Seat");
        }
    }
}
