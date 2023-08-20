package demo.DatabaseLayer.Repository;

import demo.DatabaseLayer.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findMovieByMovieId(String movieId);
}
