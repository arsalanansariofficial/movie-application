package demo.AdapterLayer.ObjectAdapter.ServiceAdapter;

import demo.AdapterLayer.ObjectAdapter.Model.MovieModel;
import demo.AdapterLayer.ObjectAdapter.ObjectAdapter.ObjectAdapter;
import demo.DatabaseLayer.Entity.Movie;
import demo.DatabaseLayer.Repository.MovieRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjectService {
    private final MovieRepository movieRepository;
    private final ObjectAdapter objectAdapter;

    @Autowired
    public ObjectService(MovieRepository movieRepository, ObjectAdapter objectAdapter) {
        this.movieRepository = movieRepository;
        this.objectAdapter = objectAdapter;
    }

    public MovieModel findMovieByMovieId(String movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("movie with movieId '" + movieId + "' not found!"));
        return objectAdapter.getMovie(movie, objectAdapter.getCityTheatresMap(movie));
    }

    public List<MovieModel> findAll() {
        if (movieRepository.findAll().isEmpty()) throw new EntityNotFoundException("no movies in the database!");
        return movieRepository.findAll().stream().filter(Movie::getReleasedStatus).map(movie -> objectAdapter.getMovie(movie, objectAdapter.getCityTheatresMap(movie))).collect(Collectors.toList());
    }
}
