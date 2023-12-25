package com.roland.movietheater_jdbc.controller.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CineMovieEventApiResponseForUser {
    private int cinemaId;
    private int movieId;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;

}
