package demo;

import demo.ControllerLayer.MovieController;
import demo.ControllerLayer.MovieTheatreController;
import demo.ControllerLayer.TheatreController;
import demo.DatabaseLayer.Entity.Movie;
import demo.DatabaseLayer.Entity.MovieTheatre;
import demo.DatabaseLayer.Entity.Theatre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieTheatreControllerTest {

    @Autowired
    private MovieTheatreController movieTheatreController;

    @Autowired
    private MovieController movieController;

    @Autowired
    private TheatreController theatreController;

    @Before
    public void save_movie_Theatre_before_each_test() {
        Movie savedMovie = movieController.saveMovie(Movie.builder().movieId("movie-1").name("movie-1").releaseDate(LocalDateTime.of(2021, 1, 1, 10, 0)).build()).getBody();
        Theatre savedTheatre = theatreController.saveTheatre(Theatre.builder().theatreId("pvr-delhi").theatreName("pvr-delhi").build()).getBody();

        MovieTheatre movieTheatre = MovieTheatre.builder().movieTheatreId("movie-1: pvr-delhi").movie(savedMovie).theatre(savedTheatre).build();
        movieTheatreController.saveMovieTheatre(movieTheatre);
    }

    @After
    public void delete_movie_Theatres_after_each_test() {
        movieTheatreController.deleteAll();
    }

    @Test
    public void should_not_find_movie_Theatre_with_invalid_movieTheatre_id() {
        MovieTheatre movieTheatre = MovieTheatre.builder().build();
        assertThrows(Exception.class, () -> movieTheatreController.getMovieTheatreByMovieTheatreId(movieTheatre));
    }

    @Test
    public void should_find_movie_Theatre_with_valid_movieTheatre_id() {
        MovieTheatre movieTheatre = MovieTheatre.builder().movieTheatreId("movie-1: pvr-delhi").build();
        assertNotNull(movieTheatreController.getMovieTheatreByMovieTheatreId(movieTheatre).getBody());
    }

    @Test
    public void should_save_movie_Theatre_with_valid_fields() {
        MovieTheatre movieTheatre = MovieTheatre.builder().movie(Movie.builder().movieId("movie-1").build()).theatre(Theatre.builder().theatreId("pvr-delhi").build()).build();
        assertNotNull(movieTheatreController.saveMovieTheatre(movieTheatre).getBody());
    }

    @Test
    public void should_not_delete_movie_Theatre_with_invalid_movieTheatre_id() {
        MovieTheatre movieTheatre = MovieTheatre.builder().build();
        assertThrows(Exception.class, () -> movieTheatreController.deleteMovieTheatreById(movieTheatre));
    }

}
