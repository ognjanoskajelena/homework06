package mk.ukim.finki.hci.homework06.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1024)
    private String description;

    private LocalDate date;

    private LocalTime time;

    private boolean isClosed;

    private Integer goingCount;

    @JsonIgnore
    @ManyToOne
    private Initiative initiative;

    @JsonIgnore
    @ManyToMany
    private List<Participant> goingParticipants;

    public Event() {
        this.goingParticipants = new ArrayList<>();
        this.isClosed = false;
    }

    public Event(String title, String description, LocalDate date, LocalTime time, Initiative initiative) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.isClosed = false;
        this.goingCount = 0;
        this.initiative = initiative;
        this.goingParticipants = new ArrayList<>();
    }

    public void addToGoingParticipants(Participant participant) {
        this.goingCount += 1;
        this.goingParticipants.add(participant);
    }

    public void removeFromGoingParticipants(Participant participant) {
        this.goingCount -= 1;
        this.goingParticipants.remove(participant);
    }

    public void close() {
        this.isClosed = true;
    }
}
