package demo.ServiceLayer.Service;

import demo.DatabaseLayer.Entity.MovieTheatre;
import demo.DatabaseLayer.Repository.MovieTheatreRepository;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieTheatreService {

    private final MovieTheatreRepository movieTheatreRepository;

    @Autowired
    public MovieTheatreService(MovieTheatreRepository movieTheatreRepository) {
        this.movieTheatreRepository = movieTheatreRepository;
    }

    public MovieTheatre findMovieTheatreByMovieTheatreId(String movieTheatreId) {
        return movieTheatreRepository.findById(movieTheatreId).orElseThrow(() -> new EntityNotFoundException("movieTheatre with movieTheatreId '" + movieTheatreId + "' not found!"));
    }

    public List<MovieTheatre> findAll() {
        if (movieTheatreRepository.findAll().isEmpty()) throw new EntityNotFoundException("no movies in the database!");
        return movieTheatreRepository.findAll();
    }

    public MovieTheatre saveMovieTheatre(MovieTheatre movieTheatre) {
        if (movieTheatre.getMovieTheatreId() == null) movieTheatre.setMovieTheatreId(UUID.randomUUID().toString().replaceAll("-", ""));
        return movieTheatreRepository.save(movieTheatre);
    }

    public List<MovieTheatre> saveMovieTheatres(List<MovieTheatre> movieTheatres) {
        movieTheatres.forEach(movieTheatre -> {
            if (movieTheatre.getMovieTheatreId() == null) movieTheatre.setMovieTheatreId(UUID.randomUUID().toString().replaceAll("-", ""));
        });
        return movieTheatreRepository.saveAll(movieTheatres);
    }

    public void deleteMovieTheatreById(String movieTheatreId) {
        movieTheatreRepository.findById(movieTheatreId).orElseThrow(() -> new EntityNotFoundException("movieTheatre with movieTheatreId '" + movieTheatreId + "' not found!"));
        movieTheatreRepository.delete(movieTheatreRepository.findMovieTheatreByMovieTheatreId(movieTheatreId));
    }

    public void deleteAll() {
        movieTheatreRepository.deleteAll();
    }
}
