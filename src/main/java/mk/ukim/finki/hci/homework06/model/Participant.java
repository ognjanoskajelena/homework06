package mk.ukim.finki.hci.homework06.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @ManyToMany(mappedBy = "goingParticipants", cascade = CascadeType.REMOVE)
    private List<Event> events;

    @JsonIgnore
    @ManyToMany
    private List<Initiative> initiatives;

    public Participant() {
        super();
        this.initiatives = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public Participant(String name, String surname, String username, String password, String email,
                       LocalDate birthDate, List<Initiative> initiatives) {
        super(name, surname, username, password, email, birthDate, Role.ROLE_PARTICIPANT);
        this.initiatives = initiatives;
        this.events = new ArrayList<>();
    }

    public Participant(String name, String surname, String username, String password, String email,
                       LocalDate birthDate) {
        super(name, surname, username, password, email, birthDate, Role.ROLE_PARTICIPANT);
        this.initiatives = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void addToInitiatives(Initiative initiative) {
        this.initiatives.add(initiative);
    }

    public void removeFromInitiatives(Initiative initiative) {
        this.initiatives.remove(initiative);
    }
}
