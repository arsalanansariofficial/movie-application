package demo.ControllerLayer;

import demo.DatabaseLayer.Entity.Movie;
import demo.ServiceLayer.Service.MovieService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    public Map<String, Object> getMovieObject(Movie movie) {
        Map<String, Object> movieObject = new HashMap<>();
        movieObject.put("movieId", movie.getMovieId());
        movieObject.put("name", movie.getName());
        movieObject.put("genre", movie.getGenre());
        movieObject.put("artists", movie.getArtists());
        movieObject.put("releaseDate", movie.getReleaseDate().toString());
        movieObject.put("duration", movie.getDuration());
        movieObject.put("criticsRating", movie.getCriticsRating());
        movieObject.put("storyLine", movie.getStoryline());
        movieObject.put("artistURL", movie.getArtistURL());
        movieObject.put("wikipediaURL", movie.getWikipediaURL());
        movieObject.put("trailerURL", movie.getTrailerURL());
        movieObject.put("posterURL", movie.getPosterURL());
        return movieObject;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Map<String, Object>> findMovieByMovieId(@RequestBody Movie movie) {
        return new ResponseEntity<>(getMovieObject(movieService.findMovieByMovieId(movie.getMovieId())), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        return new ResponseEntity<>(movieService.findAll().stream().map(this::getMovieObject).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Movie> saveMovie(@Valid @RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.CREATED);
    }

    @PostMapping(value = "/save-all")
    public ResponseEntity<List<Movie>> saveMovies(@Valid @RequestBody List<Movie> movies) {
        return new ResponseEntity<>(movieService.saveMovies(movies), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Movie> deleteMovieById(@RequestBody Movie movie) {
        Movie savedMovie = movieService.findMovieByMovieId(movie.getMovieId());
        movieService.deleteMovieByMovieId(movie.getMovieId());
        return new ResponseEntity<>(savedMovie, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<List<Movie>> deleteAll() {
        List<Movie> savedMovies = movieService.findAll();
        movieService.deleteAll();
        return new ResponseEntity<>(savedMovies, HttpStatus.OK);
    }
}
