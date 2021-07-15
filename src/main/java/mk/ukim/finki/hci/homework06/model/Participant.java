package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.Role;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Participant extends User {

    private LocalDate birthDate;

    /*
    @ManyToMany(mappedBy = "goingParticipants", cascade = CascadeType.REMOVE)
    private List<Event> events;

    @ManyToMany(mappedBy = "interestedParticipants", cascade = CascadeType.REMOVE)
    private List<Webinar> webinars;
    */

    @ManyToMany
    private List<Initiative> initiatives;

    public Participant() {
        super();
        this.initiatives = new ArrayList<>();
    }

    public Participant(String name, String surname, String username, String password, String email,
                       LocalDate birthDate, List<Initiative> initiatives) {
        super(name, surname, username, password, email, Role.ROLE_PARTICIPANT);
        this.birthDate = birthDate;
        this.initiatives = initiatives;
    }

    public Participant(String name, String surname, String username, String password, String email,
                       LocalDate birthDate) {
        super(name, surname, username, password, email, Role.ROLE_PARTICIPANT);
        this.birthDate = birthDate;
        this.initiatives = new ArrayList<>();
    }
}
