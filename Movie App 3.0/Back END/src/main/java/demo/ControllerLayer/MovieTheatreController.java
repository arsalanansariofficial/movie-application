package demo.ControllerLayer;

import demo.DatabaseLayer.Entity.MovieTheatre;
import demo.ServiceLayer.Service.MovieTheatreService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movie-theatre")
public class MovieTheatreController {
    private final MovieTheatreService movieTheatreService;

    @Autowired
    public MovieTheatreController(MovieTheatreService movieTheatreService) {
        this.movieTheatreService = movieTheatreService;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<MovieTheatre> getMovieTheatreByMovieTheatreId(@RequestBody MovieTheatre movieTheatre) {
        return new ResponseEntity<>(movieTheatreService.findMovieTheatreByMovieTheatreId(movieTheatre.getMovieTheatreId()), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<MovieTheatre>> findAll() {
        return new ResponseEntity<>(movieTheatreService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<MovieTheatre> saveMovieTheatre(@Valid @RequestBody MovieTheatre movieTheatre) {
        return new ResponseEntity<>(movieTheatreService.saveMovieTheatre(movieTheatre), HttpStatus.CREATED);
    }

    @PostMapping(value = "/save-all")
    public ResponseEntity<List<MovieTheatre>> saveMovieTheatres(@Valid @RequestBody List<MovieTheatre> movieTheatres) {
        return new ResponseEntity<>(movieTheatreService.saveMovieTheatres(movieTheatres), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<MovieTheatre> deleteMovieTheatreById(@RequestBody MovieTheatre movieTheatre) {
        MovieTheatre savedMovieTheatre = movieTheatreService.findMovieTheatreByMovieTheatreId(movieTheatre.getMovieTheatreId());
        movieTheatreService.deleteMovieTheatreById(movieTheatre.getMovieTheatreId());
        return new ResponseEntity<>(savedMovieTheatre, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<List<MovieTheatre>> deleteAll() {
        List<MovieTheatre> savedTheatres = movieTheatreService.findAll();
        movieTheatreService.deleteAll();
        return new ResponseEntity<>(savedTheatres, HttpStatus.OK);
    }
}
