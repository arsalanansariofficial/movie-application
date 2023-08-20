package demo.AuthenticationLayer.DatabaseLayer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "email cannot be blank")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "name cannot be blank")
    private String name;

    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = "phone number cannot be blank")
    private String phoneNumber;

    @Column(name = "role", nullable = false)
    @NotBlank(message = "Role cannot be blank")
    private String role = "ROLE_USER";
}
