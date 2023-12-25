package com.roland.movietheater_jdbc.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRatingForm {
    private  int customerId;
    private  int movieId;
    private String customerUserName;
    private double movieReviewRating;
    private String movieReviewComment;
}
