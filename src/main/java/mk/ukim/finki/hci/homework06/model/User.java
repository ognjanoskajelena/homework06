package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String username;

    private String password;

    private String email;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String name, String surname, String username, String password, String email, LocalDate birthDate,
                Role role) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.role = role;
    }
}
