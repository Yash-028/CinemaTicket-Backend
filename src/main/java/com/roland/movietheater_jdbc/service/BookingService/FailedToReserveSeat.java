package com.roland.movietheater_jdbc.service.BookingService;

public class FailedToReserveSeat extends Exception  {

    public FailedToReserveSeat(String message) {
        super(message);
    }
}
