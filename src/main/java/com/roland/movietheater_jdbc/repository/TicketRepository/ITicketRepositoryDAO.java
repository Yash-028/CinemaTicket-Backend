package com.roland.movietheater_jdbc.repository.TicketRepository;

import com.roland.movietheater_jdbc.service.SeatService.FailedToReserveSeatInCinemaBranch;

public interface ITicketRepositoryDAO {
    int createTicket(int userID, double ticketPrice) throws FailedToReserveSeatInCinemaBranch;
}
