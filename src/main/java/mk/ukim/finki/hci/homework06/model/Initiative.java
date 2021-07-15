package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Initiative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Integer participantsCount;

    @ManyToOne
    private Initiator initiator;

    /*
    @OneToMany(mappedBy = "initiative")
    private List<Event> events;

    @OneToMany(mappedBy = "initiative")
    private List<Poll> polls;

    @OneToMany(mappedBy = "initiative")
    private List<Discussion> discussions;

    @ManyToMany(mappedBy = "initiatives", cascade = CascadeType.REMOVE)
    private List<Participant> participants;
    */

    public Initiative() {
    }

    public Initiative(String title, String description, Initiator initiator) {
        this.title = title;
        this.description = description;
        this.participantsCount = 0;
        this.initiator = initiator;
    }
}