package com.roland.movietheater_jdbc.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CineRoomMovieEvent {
    private String movieName;
    int movieEventId;
    int movieId;
    int roomId;
    private double ticketPrice;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieStartTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieEndTime;
}
