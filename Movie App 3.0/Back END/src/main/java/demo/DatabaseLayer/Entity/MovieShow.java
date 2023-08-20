package demo.DatabaseLayer.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie_show")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieShow {
    @Id
    @Column(name = "movie_show_id", nullable = false, unique = true)
    private String movieShowId;

    @Column(name = "show_language", nullable = false)
    @NotBlank(message = "show language can not be empty!")
    private String showLanguage;

    @Column(name = "show_time", nullable = false)
    private LocalDateTime showTime;

    @Column(name = "seats_available", nullable = false)
    private Integer seatsAvailable;

    @ManyToOne
    @JoinColumn(name = "movie_theatre_id")
    private MovieTheatre movieTheatre;
}
