package demo.ControllerLayer;

import demo.DatabaseLayer.Entity.City;
import demo.ServiceLayer.Service.CityService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/find")
    public ResponseEntity<City> findCityByCityId(@RequestBody City city) {
        return new ResponseEntity<>(cityService.findCityByCityId(city.getCityId()), HttpStatus.OK);
    }

    @GetMapping(value = "/find-all")
    public ResponseEntity<List<City>> findAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<City> saveCity(@Valid @RequestBody City city) {
        return new ResponseEntity<>(cityService.saveCity(city), HttpStatus.CREATED);
    }

    @PostMapping(value = "/save-all")
    public ResponseEntity<List<City>> saveCities(@Valid @RequestBody List<City> cities) {
        return new ResponseEntity<>(cityService.saveCities(cities), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<City> deleteCityById(@RequestBody City city) {
        City savedCity = cityService.findCityByCityId(city.getCityId());
        cityService.deleteCityByCityId(city.getCityId());
        return new ResponseEntity<>(savedCity, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-all")
    public ResponseEntity<List<City>> deleteAll() {
        List<City> savedCities = cityService.findAll();
        cityService.deleteAll();
        return new ResponseEntity<>(savedCities, HttpStatus.OK);
    }
}
