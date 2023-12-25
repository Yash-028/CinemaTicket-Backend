package com.roland.movietheater_jdbc.controller.cinema;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CinemaBranchApiRequestForAdmin {


    private String cinemaName;
    private String cinemaAddress;
    private String cinemaPhone;
    private String cinemaManager;
    private int cinemaSeatCapacity;
}
