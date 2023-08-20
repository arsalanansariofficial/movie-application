package demo.ServiceLayer.Service;

import demo.DatabaseLayer.Entity.MovieShow;
import demo.DatabaseLayer.Repository.MovieShowRepository;
import demo.ServiceLayer.Exception.Definition.DuplicateEntryException;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MovieShowService {

    private final MovieShowRepository movieShowRepository;

    @Autowired
    public MovieShowService(MovieShowRepository movieShowRepository) {
        this.movieShowRepository = movieShowRepository;
    }

    public MovieShow findMovieShowByShowMovieShowId(String showId) {
        return movieShowRepository.findById(showId).orElseThrow(() -> new EntityNotFoundException("movieShow with showId '" + showId + "' not found!"));
    }

    public List<MovieShow> findAll() {
        if (movieShowRepository.findAll().isEmpty()) throw new EntityNotFoundException("no movie shows in the database!");
        return movieShowRepository.findAll();
    }

    public MovieShow saveMovieShow(MovieShow movieShow) {
        if (movieShow.getMovieShowId() == null) movieShow.setMovieShowId(UUID.randomUUID().toString().replaceAll("-", ""));
        MovieShow savedMovieShow = movieShowRepository.findMovieShowByMovieTheatreAndShowTime(movieShow.getMovieTheatre(), movieShow.getShowTime());
        if (savedMovieShow == null)
            return movieShowRepository.save(movieShow);
        throw new DuplicateEntryException("duplicate entry found", savedMovieShow);
    }

    public void updateMovieShow(MovieShow movieShow) {
        movieShowRepository.save(movieShow);
    }

    public List<MovieShow> saveMovieShows(List<MovieShow> movieShows) {
        movieShows.forEach(movieShow -> {
            if (movieShow.getMovieShowId() == null) movieShow.setMovieShowId(UUID.randomUUID().toString().replaceAll("-", ""));
        });
        return movieShowRepository.saveAll(movieShows);
    }

    public void deleteMovieShowByMovieShowId(String showId) {
        movieShowRepository.findById(showId).orElseThrow(() -> new EntityNotFoundException("movieShow with showId '" + showId + "' not found!"));
        movieShowRepository.delete(movieShowRepository.findMovieShowByMovieShowId(showId));
    }

    public void deleteAll() {
        movieShowRepository.deleteAll();
    }
}
