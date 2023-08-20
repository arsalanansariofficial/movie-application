package demo.AuthenticationLayer.DatabaseLayer.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @Column(name = "access_token", nullable = false, unique = true)
    private String accessToken;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "email")
    private User user;
}
