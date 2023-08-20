package demo;

import demo.ControllerLayer.MovieController;
import demo.DatabaseLayer.Entity.Movie;
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
public class MovieControllerTest {

    @Autowired
    private MovieController movieController;

    @Before
    public void save_movie_before_each_test() {
        Movie movie = Movie.builder().movieId("movie-1").name("movie-1").releaseDate(LocalDateTime.of(201, 1, 1, 10, 0)).build();
        movieController.saveMovie(movie);
    }

    @After
    public void delete_movies_after_each_test() {
        movieController.deleteAll();
    }

    @Test
    public void should_not_find_movie_with_invalid_movie_id() {
        Movie movie = Movie.builder().build();
        assertThrows(Exception.class, () -> movieController.findMovieByMovieId(movie));
    }

    @Test
    public void should_find_movie_with_valid_movie_id() {
        Movie movie = Movie.builder().movieId("movie-1").build();
        assertNotNull(movieController.findMovieByMovieId(movie).getBody());
    }

    @Test
    public void should_not_save_movie_without_movie_name() {
        Movie movie = Movie.builder().releaseDate(LocalDateTime.of(2021, 1, 1, 10, 0)).build();
        assertThrows(Exception.class, () -> movieController.saveMovie(movie));
    }

    @Test
    public void should_not_save_movie_without_movie_release_date() {
        Movie movie = Movie.builder().name("movie-1").build();
        assertThrows(Exception.class, () -> movieController.saveMovie(movie));
    }

    @Test
    public void should_not_save_movie_with_duplicate_movie_name() {
        Movie movie = Movie.builder().name("movie-1").releaseDate(LocalDateTime.of(2021, 1, 1, 10, 0)).build();
        assertThrows(Exception.class, () -> movieController.saveMovie(movie));
    }

    @Test
    public void should_save_movie_with_valid_fields() {
        Movie movie = Movie.builder().movieId("movie-2").name("movie-2").releaseDate(LocalDateTime.of(2021, 1, 10, 10, 0)).build();
        assertNotNull(movieController.saveMovie(movie).getBody());
    }

    @Test
    public void should_not_delete_movie_with_invalid_movie_id() {
        Movie movie = Movie.builder().build();
        assertThrows(Exception.class, () -> movieController.deleteMovieById(movie));
    }

}
