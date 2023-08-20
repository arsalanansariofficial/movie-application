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

@Entity
@Table(name = "city")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @Column(name = "city_id", nullable = false, unique = true)
    private String cityId;

    @Column(name = "city_name", unique = true)
    @NotBlank(message = "city name must not be empty!")
    private String cityName;
}
