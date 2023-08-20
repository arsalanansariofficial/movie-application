package demo.ServiceLayer.Service;

import demo.DatabaseLayer.Entity.*;
import demo.DatabaseLayer.Repository.*;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieByMovieId(String movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("movie with movieId '" + movieId + "' not found!"));
    }

    public List<Movie> findAll() {
        if (movieRepository.findAll().isEmpty()) throw new EntityNotFoundException("no movies in the database!");
        return movieRepository.findAll();
    }

    public Movie saveMovie(Movie movie) {
        if (movie.getMovieId() == null) movie.setMovieId(UUID.randomUUID().toString().replaceAll("-", ""));
        return movieRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        movies.forEach(movie -> {
            if (movie.getMovieId() == null) movie.setMovieId(UUID.randomUUID().toString().replaceAll("-", ""));
        });
        return movieRepository.saveAll(movies);
    }

    public void deleteMovieByMovieId(String movieId) {
        movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("movie with movieId '" + movieId + "' not found!"));
        movieRepository.delete(movieRepository.findMovieByMovieId(movieId));
    }

    public void deleteAll() {
        movieRepository.deleteAll();
    }

}
