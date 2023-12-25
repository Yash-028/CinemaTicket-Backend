package com.roland.movietheater_jdbc.controller.cinemaRating;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CinemaRatingApiRequestForUser {

    private double cinemaReviewRating;
    private String cinemaRatingComment;
}
