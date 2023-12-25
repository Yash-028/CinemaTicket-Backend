package com.roland.movietheater_jdbc.controller.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationApiResponseForUser {
    private int customerId;
    private int bookingId;
    private String customerUsername;
    private String movieName;
    private String cinemaAddress;
    private String roomName;
    private int seatRow;
    private int seatColumn;
    private double ticketPrice;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieStartTime;
}
