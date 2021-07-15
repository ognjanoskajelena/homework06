package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.Role;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Initiator extends User {

    private LocalDate birthDate;

    private String phone;

    /*
    @OneToMany(mappedBy = "initiator", orphanRemoval = true)
    private List<Initiative> initiatives;

    @OneToMany(mappedBy = "initiator", orphanRemoval = true)
    private List<Webinar> webinars;
    */

    public Initiator() {
        super();
    }

    public Initiator(String name, String surname, String username, String password, String email,
                     LocalDate birthDate, String phone) {
        super(name, surname, username, password, email, Role.ROLE_INITIATOR);
        this.birthDate = birthDate;
        this.phone = phone;
    }
}
