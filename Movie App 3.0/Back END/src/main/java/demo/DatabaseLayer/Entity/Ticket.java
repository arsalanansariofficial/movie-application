package demo.DatabaseLayer.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "ticket")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @Column(name = "ticket_id", nullable = false, unique = true)
    private String ticketId;

    @Column(name = "user_id", nullable = false)
    @NotBlank(message = "userId must not be empty!")
    private String userId;

    @Column(name = "movie_id", nullable = false)
    @NotBlank(message = "movieId must not be empty!")
    private String movieId;

    @Column(name = "city_id", nullable = false)
    @NotBlank(message = "cityId must not be empty!")
    private String cityId;

    @Column(name = "theatre_id", nullable = false)
    @NotBlank(message = "theatreId must not be empty!")
    private String theatreId;

    @Column(name = "movie_show_language", nullable = false)
    @NotBlank(message = "show language must not be empty!")
    private String movieShowLanguage;

    @Column(name = "movie_show_id", nullable = false)
    @NotBlank(message = "showId must not be empty!")
    private String movieShowId;

    @Column(name = "number_of_tickets", nullable = false)
    @NotBlank(message = "ticket count must not be empty!")
    private String numberOfTickets;

    @Column(name = "ticket_price", nullable = false)
    @NotBlank(message = "ticket price must not be empty!")
    private String ticketPrice;
}
