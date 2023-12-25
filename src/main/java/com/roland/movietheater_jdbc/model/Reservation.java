package com.roland.movietheater_jdbc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Reservation {
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
