package com.roland.movietheater_jdbc.controller.movieEvent;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CineRoomMovieEventApiResponseForAdmin {
    private String movieName;
    int movieEventId;
    int movieId;
    int roomId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")

    private Date movieStartTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieEndTime;
    private double ticketPrice;
}
