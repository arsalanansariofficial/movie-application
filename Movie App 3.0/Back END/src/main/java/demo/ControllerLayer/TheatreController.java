package demo.ControllerLayer;

import demo.DatabaseLayer.Entity.Theatre;
import demo.ServiceLayer.Service.TheatreService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/theatre")
public class TheatreController {

    private final TheatreService theatreService;

    @Autowired
    public TheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<Theatre> findTheatreByTheatreId(@RequestBody Theatre theatre) {
        return new ResponseEntity<>(theatreService.findTheatreByTheatreId(theatre.getTheatreId()), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<Theatre>> findAll() {
        return new ResponseEntity<>(theatreService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Theatre> saveTheatre(@Valid @RequestBody Theatre city) {
        return new ResponseEntity<>(theatreService.saveTheatre(city), HttpStatus.CREATED);
    }

    @PostMapping(value = "/save-all")
    public ResponseEntity<List<Theatre>> saveTheatres(@Valid @RequestBody List<Theatre> theatres) {
        return new ResponseEntity<>(theatreService.saveTheatres(theatres), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Theatre> deleteTheatreById(@RequestBody Theatre theatre) {
        Theatre savedTheatre = theatreService.findTheatreByTheatreId(theatre.getTheatreId());
        theatreService.deleteTheatreByTheatreId(theatre.getTheatreId());
        return new ResponseEntity<>(savedTheatre, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<List<Theatre>> deleteAll() {
        List<Theatre> savedTheatres = theatreService.findAll();
        theatreService.deleteAll();
        return new ResponseEntity<>(savedTheatres, HttpStatus.OK);
    }

}
