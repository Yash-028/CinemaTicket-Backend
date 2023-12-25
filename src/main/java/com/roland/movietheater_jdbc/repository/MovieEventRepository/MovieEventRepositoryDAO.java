package com.roland.movietheater_jdbc.repository.MovieEventRepository;

import com.roland.movietheater_jdbc.model.CineRoomMovieEvent;
import com.roland.movietheater_jdbc.model.CinemaBranch;
import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.model.MovieEvent;
import com.roland.movietheater_jdbc.repository.MovieRepository.MovieMapper;
import com.roland.movietheater_jdbc.service.MovieEventService.FailedToCreateMovieEventException;
import com.roland.movietheater_jdbc.service.MovieEventService.FailedToDeleteMovieEventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieEventRepositoryDAO implements IMovieEventRepositoryDAO {

    private static final String SQL_STATEMENT_TO_FIND_MOVIE_SHOWINGS_IN_ALL_BRANCHES =
    "select distinct movie.* from movie_event, room, cinemabranch,movie " +
            "where movie_event.room_id = room.room_id AND movie_event.movie_id = movie.movie_id " +
            "AND room.cinema_branch = cinemabranch.cinema_id";

    private static final String SQL_STATEMENT_TO_CREATE_MOVIE_EVENT =
            "Insert into movie_event (movie_id,room_id, movie_start_time, movie_end_time,ticket_price) values (?,?,?,?,?)";

    private static final String SQL_STATEMENT_TO_DELETE_A_MOVIE_EVENT_IN_A_CINEMABRANCH
            ="DELETE FROM movie_event WHERE movie_eventId = ?  and movie_id = ? and  room_id = ? ";

    private static final String SQL_STATEMENT_TO_GET_MOVEI_EVENT_TIMING =
            "select * from movie_event where movie_id = ?";

    private static final String SQL_STATEMENT_TO_FIND_MOVIE_SHOWING_IN_A_ROOM_THAT_BELONGS_TO_CINEMABRANCH=
          "select M.movie_name , ME.*   from movie_event ME, movie M where ME.movie_id = M.movie_id and room_id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<Movie> findAllMovieEvents() {
        List<Movie> allMovieEvents = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_MOVIE_SHOWINGS_IN_ALL_BRANCHES, new MovieMapper());
        return allMovieEvents;
    }

    @Override
    public MovieEvent createMovieEvent(MovieEvent movieEvent) throws FailedToCreateMovieEventException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_CREATE_MOVIE_EVENT,
                    movieEvent.getMovieId(),
                    movieEvent.getRoomId(),
                    movieEvent.getMovieStartTime(),
                    movieEvent.getMovieEndTime(),
                    movieEvent.getTicketPrice());

            return movieEvent;

        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw new FailedToCreateMovieEventException(e, movieEvent.getRoomId(), movieEvent.getMovieId());
        }

    }

    @Override
    public String deleteMovieEvent(int cinemaId, int roomId, int movieId, int movieEvent) throws FailedToDeleteMovieEventException {
        try {
            jdbcTemplate.update(SQL_STATEMENT_TO_DELETE_A_MOVIE_EVENT_IN_A_CINEMABRANCH,
                            movieEvent,movieId,roomId);
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            throw  new FailedToDeleteMovieEventException(e, roomId, movieId);
        }

        return  "Removed movie event where movieId: " + movieId + " in roomId: " + roomId + " Where movie event Id was:  " + movieEvent;
    }

    @Override
    public List<MovieEvent> getMovieEventTiming(int movieId) {
        List<MovieEvent> movieEventTimings = jdbcTemplate.query(SQL_STATEMENT_TO_GET_MOVEI_EVENT_TIMING, new  MovieEventMapper(), movieId);
        return movieEventTimings;
    }

    @Override
    public List<CineRoomMovieEvent> getCineRoomMovieEvent(int cinemaId, int roomId) {
        List<CineRoomMovieEvent> cineRoomMovieEvents = jdbcTemplate.query(SQL_STATEMENT_TO_FIND_MOVIE_SHOWING_IN_A_ROOM_THAT_BELONGS_TO_CINEMABRANCH,
                new CineRoomMovieEventMapper(), roomId);

        return cineRoomMovieEvents;
    }


}
