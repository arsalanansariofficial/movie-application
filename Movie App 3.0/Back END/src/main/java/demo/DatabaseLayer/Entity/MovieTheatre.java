package demo.DatabaseLayer.Entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_theatre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieTheatre {
    @Id
    @Column(name = "movie_theatre_id", nullable = false, unique = true)
    private String movieTheatreId;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;
}
