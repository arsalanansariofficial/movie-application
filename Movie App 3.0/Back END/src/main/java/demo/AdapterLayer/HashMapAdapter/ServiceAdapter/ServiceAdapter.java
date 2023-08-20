package demo.AdapterLayer.HashMapAdapter.ServiceAdapter;

import demo.DatabaseLayer.Entity.Movie;
import demo.DatabaseLayer.Repository.MovieRepository;
import demo.AdapterLayer.HashMapAdapter.EntityAdapter.EntityAdapter;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceAdapter {
    private final MovieRepository movieRepository;
    private final EntityAdapter entityAdapter;

    @Autowired
    public ServiceAdapter(MovieRepository movieRepository, EntityAdapter entityAdapter) {
        this.movieRepository = movieRepository;
        this.entityAdapter = entityAdapter;
    }

    public Map<String, Object> findMovieByMovieId(String movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("movie with movieId '" + movieId + "' not found!"));
        return entityAdapter.getMovie(movie, entityAdapter.getCityTheatresMap(movie));
    }

    public List<Map<String, Object>> findAll() {
        if (movieRepository.findAll().isEmpty()) throw new EntityNotFoundException("no movies in the database!");
        return movieRepository.findAll().stream().map(movie -> entityAdapter.getMovie(movie, entityAdapter.getCityTheatresMap(movie))).collect(Collectors.toList());
    }
}
