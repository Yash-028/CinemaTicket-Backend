package com.roland.movietheater_jdbc.controller.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CineMovieEventRoomTimingApiResponseForUser {
    private int cinemaId;
    private int movieId;
    private int room_id;
    private int movieEventId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieStartTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date movieEndTime;
}
