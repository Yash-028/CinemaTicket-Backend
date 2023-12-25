package com.roland.movietheater_jdbc.repository.MovieRepository;

import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.service.MovieService.FailedToDeleteMovieException;
import com.roland.movietheater_jdbc.service.MovieService.FailedToFindMovieExcpetion;
import com.roland.movietheater_jdbc.service.MovieService.FailedToInsertMovieException;
import com.roland.movietheater_jdbc.service.MovieService.FailedToUpdateMovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieRepositoryDAO implements IMovieRepositoryDAO {



    private static String SQL_STATEMENT_TO_FIND_ALL_MOVIES =
            "select * from movie";

    private static final String SQL_STATEMENT_TO_CREATE_MOVIE =
            "insert into movie (movie_name,movie_genre,movie_duration,movie_description,movie_releaseDate,movie_directors,movie_stars,movie_urlImage,movie_urlPosterImage) values (?,?,?,?,?,?,?,?,?)";



    private static final String SQL_STATEMENT_TO_DELETE_MOVIE_IN_BRANCH =
            "delete from movie where movie_id =?";

    private static final String SQL_STATEMENT_TO_UPDATE_MOVIE_IN_BRANCH =
            "update movie set  movie_name = ? , movie_genre = ? , movie_duration= ? , movie_description = ? , movie_releaseDate = ? , movie_directors = ? , movie_stars = ? , movie_urlImage = ? , movie_urlPosterImage= ? where movie_id = ?";


    private static final String  SQL_STATEMENT_FIND_MOVIE_BY_ID = "select * from movie where movie_id = ? ";

    private static final String SQL_STATEMENT_TO_MOVIE_BY_SEARCHING = "select * from movie where movie_name Like '";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public List<Movie> findAllMovies() {
        List<Movie> movieAtCurrentCinemaList = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_ALL_MOVIES, new MovieMapper());

        return movieAtCurrentCinemaList;

    }

    @Override
    public Movie findMovieById(int movieId) throws FailedToFindMovieExcpetion {
        try {
            List<Movie> movieAtCurrentCinemaList =  jdbcTemplate.query(SQL_STATEMENT_FIND_MOVIE_BY_ID, new MovieMapper() , movieId);
            if(movieAtCurrentCinemaList.get(0).equals(null))
                return null;
            else
              return movieAtCurrentCinemaList.get(0);

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new FailedToFindMovieExcpetion(e, movieId);
        }

    }


    @Override
    public List<Movie> SearchForMovie(String search) {
        List<Movie> movieAtCurrentCinemaList = jdbcTemplate.query(SQL_STATEMENT_TO_MOVIE_BY_SEARCHING + search + "%'", new MovieMapper());

        return movieAtCurrentCinemaList;

    }

    @Override
    public Movie CreateMovieForBranch(Movie movie) throws FailedToInsertMovieException {
        try {


            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_MOVIE
                    , movie.getMovieName()
                    , movie.getMovieGenre()
                    , movie.getMovieDuration()
                    , movie.getMovieDescription()
                    , movie.getMovieReleaseDate()
                    ,movie.getMovieDirectors()
                    ,movie.getMovieStars()
                   ,movie.getMovieUrlImage()
                   ,movie.getMovieUrlPosterImage())
            ;

            return movie;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToInsertMovieException(e, movie.getMovieId());
        }
    }

    @Override
    public int deleteMovieInBranch(int movieId) throws FailedToDeleteMovieException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_MOVIE_IN_BRANCH, movieId);
            return movieId;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToDeleteMovieException(e, movieId);
        }
    }

    @Override
    public Movie updateMovieInBranch(int movieId, Movie movie) throws FailedToUpdateMovieException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_UPDATE_MOVIE_IN_BRANCH,
                    movie.getMovieName(),
                    movie.getMovieGenre(),
                    movie.getMovieDuration(),
                    movie.getMovieDescription(),
                    movie.getMovieReleaseDate(),
                    movie.getMovieDirectors(),
                    movie.getMovieStars(),
                    movie.getMovieUrlImage(),
                    movie.getMovieUrlPosterImage(),
                    movieId);

            movie.setMovieId(movieId);

            return movie;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToUpdateMovieException(e, movieId);
        }
    }




}
