package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Initiative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1024)
    private String description;

    private LocalDate dateOpened;

    @ManyToOne
    private User initiator;

    @OneToMany(mappedBy = "initiative")
    private List<Event> events;

    @OneToMany(mappedBy = "initiative")
    private List<Poll> polls;

    @OneToMany(mappedBy = "initiative")
    private List<Discussion> discussions;

    @ManyToMany(mappedBy = "initiatives", cascade = CascadeType.REMOVE)
    private List<Participant> participants;

    public Initiative() {
        this.events = new ArrayList<>();
        this.polls = new ArrayList<>();
        this.discussions = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public Initiative(String title, String description, User initiator) {
        this.title = title;
        this.description = description;
        this.dateOpened = LocalDate.now();
        this.initiator = initiator;
        this.events = new ArrayList<>();
        this.polls = new ArrayList<>();
        this.discussions = new ArrayList<>();
        this.participants = new ArrayList<>();
    }
}
