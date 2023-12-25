package com.roland.movietheater_jdbc.service.TicketService;

import com.roland.movietheater_jdbc.service.SeatService.FailedToReserveSeatInCinemaBranch;

public interface ITicketService {
    int createTicket(int userID, double ticketPrice) throws FailedToReserveSeatInCinemaBranch;
}
