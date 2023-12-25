package com.roland.movietheater_jdbc.controller.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CineMovieEventRoomSeatApiResponseForUser {

    private int cinemaId;
    private int ticketId;
    private int movieEventId;
    private int seatId;
    private int roomIdOfSeat;
    private int seatRow;
    private int seatColumn;
    private int bookingId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date bookingDate;
    private double ticketPrice;
    private boolean seatStatus;


}
