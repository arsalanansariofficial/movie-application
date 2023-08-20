package demo.DatabaseLayer.Repository;

import demo.DatabaseLayer.Entity.Movie;
import demo.DatabaseLayer.Entity.MovieTheatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieTheatreRepository extends JpaRepository<MovieTheatre, String> {
    MovieTheatre findMovieTheatreByMovieTheatreId(String movieTheatreId);
    List<MovieTheatre> findMovieTheatresByMovie(Movie movie);
}
