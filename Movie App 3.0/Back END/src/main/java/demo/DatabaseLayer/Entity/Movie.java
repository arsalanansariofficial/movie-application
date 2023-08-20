package demo.DatabaseLayer.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @Column(name = "movie_id", nullable = false, unique = true)
    private String movieId;

    @Column(name = "name", nullable = false, unique = true)
    @NotBlank(message = "movie name can not be empty!")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "artists")
    private String artists;

    @Column(name = "release_date", nullable = false)
    private LocalDateTime releaseDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "critics_rating")
    private Integer criticsRating;

    @Column(name = "story_line")
    private String storyline;

    @Column(name = "artist_url")
    private String artistURL;

    @Column(name = "wikipedia_url")
    private String wikipediaURL;

    @Column(name = "trailer_url")
    private String trailerURL;

    @Column(name = "poster_url")
    private String posterURL;

    @Column(name = "released_status")
    private Boolean releasedStatus = true;
}
