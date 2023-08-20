package demo.ControllerLayer;

import demo.DatabaseLayer.Entity.MovieShow;
import demo.ServiceLayer.Service.MovieShowService;
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
@RequestMapping(value = "/movie-show")
public class MovieShowController {

    private final MovieShowService movieShowService;

    @Autowired
    public MovieShowController(MovieShowService movieShowService) {
        this.movieShowService = movieShowService;
    }

    public Map<String, Object> getMovieShowObject(MovieShow movieShow) {
        Map<String, Object> movieShowObject = new HashMap<>();
        movieShowObject.put("movieShowId", movieShow.getMovieShowId());
        movieShowObject.put("showLanguage", movieShow.getShowLanguage());
        movieShowObject.put("showTime", movieShow.getShowTime().toString());
        movieShowObject.put("movieTheatre", movieShow.getMovieTheatre());
        return movieShowObject;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Map<String, Object>> findMovieShowByMovieShowId(@RequestBody MovieShow movieShow) {
        return new ResponseEntity<>(getMovieShowObject(movieShowService.findMovieShowByShowMovieShowId(movieShow.getMovieShowId())), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        return new ResponseEntity<>(movieShowService.findAll().stream().map(this::getMovieShowObject).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<MovieShow> saveMovieShow(@Valid @RequestBody MovieShow MovieShow) {
        return new ResponseEntity<>(movieShowService.saveMovieShow(MovieShow), HttpStatus.CREATED);
    }

    @PostMapping(value = "/save-all")
    public ResponseEntity<List<MovieShow>> saveMovieShows(@Valid @RequestBody List<MovieShow> MovieShows) {
        return new ResponseEntity<>(movieShowService.saveMovieShows(MovieShows), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<MovieShow> deleteMovieShowByMovieShowId(@RequestBody MovieShow movieShow) {
        MovieShow savedMovieShow = movieShowService.findMovieShowByShowMovieShowId(movieShow.getMovieShowId());
        movieShowService.deleteMovieShowByMovieShowId(movieShow.getMovieShowId());
        return new ResponseEntity<>(savedMovieShow, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<List<MovieShow>> deleteAll() {
        List<MovieShow> savedMovieShows = movieShowService.findAll();
        movieShowService.deleteAll();
        return new ResponseEntity<>(savedMovieShows, HttpStatus.OK);
    }

}
