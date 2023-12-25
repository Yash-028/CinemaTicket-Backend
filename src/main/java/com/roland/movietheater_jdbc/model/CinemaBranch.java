package com.roland.movietheater_jdbc.model;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class CinemaBranch {


    private int cinemaId;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;
    private String cinemaManager;
    private int cinemaSeatCapacity;


}
