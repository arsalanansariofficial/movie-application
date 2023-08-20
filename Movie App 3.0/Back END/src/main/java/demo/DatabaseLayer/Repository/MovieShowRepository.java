package demo.DatabaseLayer.Repository;

import demo.DatabaseLayer.Entity.MovieShow;
import demo.DatabaseLayer.Entity.MovieTheatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieShowRepository extends JpaRepository<MovieShow, String> {
    List<MovieShow> findMovieShowsByMovieTheatre(MovieTheatre movieTheatre);
    MovieShow findMovieShowByMovieShowId(String movieShowId);
    MovieShow findMovieShowByMovieTheatreAndShowTime(MovieTheatre movieTheatre, LocalDateTime showTime);
}
