package com.roland.movietheater_jdbc.controller.seat;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatApiResponseForAdmin {
    private int seatId;
    private int roomId;
    private int seatRow;

    private int seatColumn;


}
