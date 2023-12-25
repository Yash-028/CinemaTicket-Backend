package com.roland.movietheater_jdbc.repository.MovieRepository;

import com.roland.movietheater_jdbc.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieMapper implements RowMapper<Movie> {



    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {

        Movie movie = new Movie();
        movie.setMovieId(resultSet.getInt("movie_id"));
        movie.setMovieName(resultSet.getString("movie_name"));
        movie.setMovieGenre(resultSet.getString("movie_genre"));
        movie.setMovieDescription(resultSet.getString("movie_description"));
        movie.setMovieDuration(resultSet.getInt("movie_duration"));
        movie.setMovieDirectors(resultSet.getString("movie_directors"));
        movie.setMovieStars(resultSet.getString("movie_stars"));
        movie.setMovieUrlImage(resultSet.getString("movie_urlImage"));
        movie.setMovieUrlPosterImage(resultSet.getString("movie_urlPosterImage"));
        movie.setMovieReleaseDate(resultSet.getDate("movie_releaseDate"));

        return movie;
    }
}
