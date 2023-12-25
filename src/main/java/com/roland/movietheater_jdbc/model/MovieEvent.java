package com.roland.movietheater_jdbc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEvent {
    private  int roomId;
    private int movieId;
    private int movieEventId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieStartTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieEndTime;
    private double ticketPrice;



}
