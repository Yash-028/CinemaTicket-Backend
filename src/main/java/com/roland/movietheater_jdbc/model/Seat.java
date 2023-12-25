package com.roland.movietheater_jdbc.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    private int seatId;
    private int roomId;
    private int seatRow;
    private int seatColumn;

}
