package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Webinar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Column(length = 1024)
    private String description;

    private String link;

    private LocalDate date;

    private LocalTime time;

    private Integer interestedCount;

    @ManyToOne
    private User initiator;

    @ManyToMany
    private List<Participant> interestedParticipants;

    public Webinar() {
        this.interestedCount = 0;
        this.interestedParticipants = new ArrayList<>();
    }

    public Webinar(String topic, String description, String link, LocalDate date, LocalTime time, User initiator) {
        this.topic = topic;
        this.description = description;
        this.link = link;
        this.date = date;
        this.time = time;
        this.interestedCount = 0;
        this.initiator = initiator;
        this.interestedParticipants = new ArrayList<>();
    }
}
