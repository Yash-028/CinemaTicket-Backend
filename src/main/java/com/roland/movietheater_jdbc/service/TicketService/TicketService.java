package com.roland.movietheater_jdbc.service.TicketService;

import com.roland.movietheater_jdbc.repository.TicketRepository.TicketRepositoryDAO;
import com.roland.movietheater_jdbc.service.SeatService.FailedToReserveSeatInCinemaBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService implements  ITicketService {

    private TicketRepositoryDAO ticketRepositoryDAO;

    @Autowired
    public TicketService(TicketRepositoryDAO ticketRepositoryDAO) {
        this.ticketRepositoryDAO = ticketRepositoryDAO;
    }

    @Override
    public int createTicket(int userID, double ticketPrice) throws FailedToReserveSeatInCinemaBranch {
        return ticketRepositoryDAO.createTicket(userID,ticketPrice);
    }
}
