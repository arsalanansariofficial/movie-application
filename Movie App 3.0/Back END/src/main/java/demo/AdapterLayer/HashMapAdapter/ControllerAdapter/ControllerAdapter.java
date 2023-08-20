package demo.AdapterLayer.HashMapAdapter.ControllerAdapter;

import demo.DatabaseLayer.Entity.Movie;
import demo.AdapterLayer.HashMapAdapter.ServiceAdapter.ServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/adapter/movie")
public class ControllerAdapter {

    private final ServiceAdapter serviceAdapter;

    @Autowired
    public ControllerAdapter(ServiceAdapter serviceAdapter) {
        this.serviceAdapter = serviceAdapter;
    }

    @GetMapping(value = "/find")
    public Map<String, Object> findMovieShowsByMovieId(@RequestBody Movie movie) {
        return serviceAdapter.findMovieByMovieId(movie.getMovieId());
    }

    @GetMapping(value = "/find-all")
    public List<Map<String, Object>> findAll() {
        return serviceAdapter.findAll();
    }
}
