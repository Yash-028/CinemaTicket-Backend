package com.roland.movietheater_jdbc.model;

import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Movie {
    private int movieId;
    private String movieName;
    private String movieGenre;
    private int movieDuration;
    private String movieDescription;
    private String movieDirectors;
    private String movieStars;
    private String movieUrlImage;
    private String movieUrlPosterImage;
    private Date movieReleaseDate;


}
