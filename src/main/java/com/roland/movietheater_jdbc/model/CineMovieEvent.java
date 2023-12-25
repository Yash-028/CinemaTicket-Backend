package com.roland.movietheater_jdbc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CineMovieEvent {
    private int cinemaId;
    private int movieId;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;

}
