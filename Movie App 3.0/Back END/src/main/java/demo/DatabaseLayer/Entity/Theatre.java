package demo.DatabaseLayer.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theatre")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {
    @Id
    @Column(name = "theatre_id", nullable = false, unique = true)
    private String theatreId;

    @Column(name = "theatre_name", nullable = false)
    @NotBlank(message = "theatre name can not be empty!")
    private String theatreName;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
