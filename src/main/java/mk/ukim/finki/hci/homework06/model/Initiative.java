package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Initiative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate dateOpened;

    private Integer participantsCount;

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
    }

    public Initiative(String title, String description, LocalDate dateOpened, User initiator) {
        this.title = title;
        this.description = description;
        this.dateOpened = dateOpened;
        this.participantsCount = 0;
        this.initiator = initiator;
    }

    public void addToEvents(Event event) {
        this.events.add(event);
    }

    public void addToPolls(Poll poll) {
        this.polls.add(poll);
    }

    public void addToDiscussions(Discussion discussion) {
        this.discussions.add(discussion);
    }

    public void addToParticipants(Participant participant) {
        this.participantsCount += 1;
        this.participants.add(participant);
    }

    public void removeFromParticipants(Participant participant) {
        this.participantsCount -= 1;
        this.participants.add(participant);
    }
}
