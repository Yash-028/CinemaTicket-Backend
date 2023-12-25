package com.roland.movietheater_jdbc.controller.movieRating;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRatingApiResponseForUser {
    private  int customerId;
    private  int movieId;
    private String customerUserName;
    private double movieReviewRating;
    private String movieReviewComment;
}
