package com.roland.movietheater_jdbc.controller.movie;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieApiRequestForAdmin {

    private String movieName;
    private String movieGenre;
    private int  movieDuration;
    private String movieDescription;
    private Date movieReleaseDate;
    private String movieDirectors;
    private String movieStars;
    private String movieUrlImage;
    private String movieUrlPosterImage;


}
