package demo;

import demo.ControllerLayer.MovieShowController;
import demo.DatabaseLayer.Entity.MovieShow;
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
public class MovieShowControllerTest {

    @Autowired
    private MovieShowController movieShowController;

    @Before
    public void save_movie_show_before_each_test() {
        MovieShow movieShow = MovieShow.builder().movieShowId("show-hindi: [movie-1: pvr-delhi]").showLanguage("hindi").showTime(LocalDateTime.of(2021, 1, 1, 10, 0)).seatsAvailable(50).build();
        movieShowController.saveMovieShow(movieShow);
    }

    @After
    public void delete_movie_shows_after_each_test() {
        movieShowController.deleteAll();
    }

    @Test
    public void should_not_find_movie_show_with_invalid_movie_show_id() {
        MovieShow movieShow = MovieShow.builder().build();
        assertThrows(Exception.class, () -> movieShowController.findMovieShowByMovieShowId(movieShow));
    }

    @Test
    public void should_find_movie_show_with_valid_movie_show_id() {
        MovieShow movieShow = MovieShow.builder().movieShowId("show-hindi: [movie-1: pvr-delhi]").build();
        assertNotNull(movieShowController.findMovieShowByMovieShowId(movieShow).getBody());
    }

    @Test
    public void should_not_save_movie_show_without_show_language() {
        MovieShow movieShow = MovieShow.builder().showTime(LocalDateTime.of(2021, 1, 1, 10, 0)).build();
        assertThrows(Exception.class, () -> movieShowController.saveMovieShow(movieShow));
    }

    @Test
    public void should_not_save_movie_show_without_show_time() {
        MovieShow movieShow = MovieShow.builder().showLanguage("hindi").build();
        assertThrows(Exception.class, () -> movieShowController.saveMovieShow(movieShow));
    }

    @Test
    public void should_not_save_movie_show_with_duplicate_show_time_and_movie_theatre() {
        MovieShow movieShow = MovieShow.builder().showLanguage("hindi").showTime(LocalDateTime.of(2021, 1, 1, 10, 0)).build();
        assertThrows(Exception.class, () -> movieShowController.saveMovieShow(movieShow).getBody());
    }

    @Test
    public void should_save_movie_show_with_valid_fields() {
        MovieShow movieShow = MovieShow.builder().showLanguage("hindi").showTime(LocalDateTime.of(2021, 1, 10, 10, 0)).seatsAvailable(50).build();
        assertNotNull(movieShowController.saveMovieShow(movieShow).getBody());
    }

    @Test
    public void should_not_delete_movieShow_with_invalid_movie_show_id() {
        MovieShow movieShow = MovieShow.builder().build();
        assertThrows(Exception.class, () -> movieShowController.deleteMovieShowByMovieShowId(movieShow));
    }

}
