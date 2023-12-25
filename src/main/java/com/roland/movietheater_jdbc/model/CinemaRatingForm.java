package com.roland.movietheater_jdbc.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CinemaRatingForm {
    private  int cinemaRatingFormId;
    private int customerId;
    private int cinemaId;
    private String CustomerUsername;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ratingDate;
    private double cinemaReviewRating;
    private String cinemaRatingComment;
}
