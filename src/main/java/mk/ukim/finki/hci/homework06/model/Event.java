package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate date;

    private LocalDate time;

    private Integer goingCount;

    @ManyToOne
    private Initiative initiative;

    @ManyToMany
    private List<Participant> goingParticipants;

    public Event() {
        this.goingParticipants = new ArrayList<>();
    }

    public Event(String title, String description, LocalDate date, LocalDate time, Initiative initiative) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.goingCount = 0;
        this.initiative = initiative;
        this.goingParticipants = new ArrayList<>();
    }
}
