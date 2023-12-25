package com.roland.movietheater_jdbc.controller.movieEvent;

import com.roland.movietheater_jdbc.controller.movie.MovieApiResponseForAdmin;
import com.roland.movietheater_jdbc.model.CineRoomMovieEvent;
import com.roland.movietheater_jdbc.model.Movie;
import com.roland.movietheater_jdbc.model.MovieEvent;
import com.roland.movietheater_jdbc.service.MovieEventService.FailedToCreateMovieEventException;
import com.roland.movietheater_jdbc.service.MovieEventService.FailedToDeleteMovieEventException;
import com.roland.movietheater_jdbc.service.MovieEventService.MovieEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieEventController {

    private MovieEventService movieEventService;


    @Autowired
    public MovieEventController(MovieEventService movieEventService) {
        this.movieEventService = movieEventService;

    }

    @GetMapping("/movieEvent")
    public ResponseEntity getAllMovieEvents() {
        List<Movie> movieList = movieEventService.findAllMovieEvents();
        List<MovieApiResponseForAdmin> responseList = buildMovieResponse(movieList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/movieEvents/{movieId}")
    public ResponseEntity getMovieEventTiming(@PathVariable("movieId") int movieId) {
        List<MovieEvent> movieEventList = movieEventService.getMovieEventTiming(movieId);
        List<MovieEventApiResponseForUserAndAdmin> responseList = buildEventMovieResponse(movieEventList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @GetMapping("/admin/showing/cinemas/{cinemaId}/rooms/{roomId}")
    public ResponseEntity getAllMovieEventInCurrentRoom(@PathVariable("cinemaId") int cinemaId,
                                                        @PathVariable("roomId") int roomId) {

        List<CineRoomMovieEvent> cineRoomMovieEventList = movieEventService.getCineRoomMovieEvent(cinemaId, roomId);
        List<CineRoomMovieEventApiResponseForAdmin> responseList = buildCineEventMovieResponse(cineRoomMovieEventList);
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }


    @DeleteMapping("/admin/showing/cinemas/{cinemaId}/rooms/{roomId}/movies/{movieId}/movieEvents/{movieEventId}")
    public ResponseEntity deleteMovieEvent(@PathVariable("cinemaId") int cinemaId,
                                           @PathVariable("roomId") int roomId,
                                           @PathVariable("movieId") int movieId,
                                           @PathVariable("movieEventId") int movieEvent) {

        try {
            String movieEventRemoved = movieEventService.deleteMovieEvent(cinemaId, roomId, movieId, movieEvent);
            return ResponseEntity.status(HttpStatus.OK).body(movieEventRemoved);
        } catch (FailedToDeleteMovieEventException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @PostMapping("/admin/showing/cinemas/{cinemaId}/rooms/{roomId}/movies/{movieId}")
    public ResponseEntity createMovieEvent(@PathVariable("cinemaId") int cinemaId,
                                           @PathVariable("roomId") int roomId, @PathVariable("movieId") int movieId,
                                           @RequestBody MovieEventApiRequestForAdmin request) {
        try {

            MovieEvent movieEvent = movieEventService.createMovieEvent(getMovieEvent(request, cinemaId, roomId, movieId));

            MovieEventApiResponseForUserAndAdmin response = getEventMovieResponse(movieEvent);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (FailedToCreateMovieEventException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }


    }

    private List<MovieEventApiResponseForUserAndAdmin> buildEventMovieResponse(List<MovieEvent> movieEventList) {
        List<MovieEventApiResponseForUserAndAdmin> responseList = new ArrayList<>();
        for (MovieEvent movieEvent : movieEventList) {
            responseList.add(getEventMovieResponse(movieEvent));
        }

        return responseList;

    }

    private MovieEventApiResponseForUserAndAdmin getEventMovieResponse(MovieEvent movieEvent) {
        return new MovieEventApiResponseForUserAndAdmin().builder()
                .movieId(movieEvent.getMovieId())
                .roomId(movieEvent.getRoomId())
                .movieEventId(movieEvent.getMovieEventId())
                .movieStartTime(movieEvent.getMovieStartTime())
                .movieEndTime(movieEvent.getMovieEndTime())
                .ticketPrice(movieEvent.getTicketPrice())
                .build();
    }

    private MovieEvent getMovieEvent(MovieEventApiRequestForAdmin request, int cinemaId, int roomId, int movieId) {
        return new MovieEvent().builder()
                .movieId(movieId)
                .roomId(roomId)
                .movieStartTime(request.getMovieStartTime())
                .movieEndTime(request.getMovieEndTime())
                .ticketPrice(request.getTicketPrice())
                .build();
    }


    private List<MovieApiResponseForAdmin> buildMovieResponse(List<Movie> movieList) {
        List<MovieApiResponseForAdmin> responseList = new ArrayList<>();
        for (Movie movie : movieList) {
            responseList.add(getMovieApiResponse(movie));
        }

        return responseList;
    }

    private MovieApiResponseForAdmin getMovieApiResponse(Movie movie) {
        return new MovieApiResponseForAdmin().builder()
                .movieId(movie.getMovieId())
                .movieName(movie.getMovieName())
                .movieDescription(movie.getMovieDescription())
                .movieDuration(movie.getMovieDuration())
                .movieGenre(movie.getMovieGenre())
                .movieDirectors(movie.getMovieDirectors())
                .movieStars(movie.getMovieStars())
                .movieUrlImage(movie.getMovieUrlImage())
                .movieUrlPosterImage(movie.getMovieUrlPosterImage())
                .movieReleaseDate(movie.getMovieReleaseDate())
                .build();

    }

    private List<CineRoomMovieEventApiResponseForAdmin> buildCineEventMovieResponse(List<CineRoomMovieEvent> cineRoomMovieEventList) {
        List<CineRoomMovieEventApiResponseForAdmin> responseList = new ArrayList<>();
        for (CineRoomMovieEvent cineRoomMovieEvent : cineRoomMovieEventList) {
            responseList.add(getCineRoomMovieApiResponse(cineRoomMovieEvent));
        }

        return responseList;
    }

    private CineRoomMovieEventApiResponseForAdmin getCineRoomMovieApiResponse(CineRoomMovieEvent cineRoomMovieEvent) {
        return new CineRoomMovieEventApiResponseForAdmin().builder()
                .movieName(cineRoomMovieEvent.getMovieName())
                .movieId(cineRoomMovieEvent.getMovieId())
                .roomId(cineRoomMovieEvent.getRoomId())
                .movieEventId(cineRoomMovieEvent.getMovieEventId())
                .movieStartTime(cineRoomMovieEvent.getMovieStartTime())
                .movieEndTime(cineRoomMovieEvent.getMovieEndTime())
                .ticketPrice(cineRoomMovieEvent.getTicketPrice())
                .build();
    }
}
