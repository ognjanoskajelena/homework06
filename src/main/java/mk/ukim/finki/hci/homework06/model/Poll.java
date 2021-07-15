package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private boolean isOpen;

    @OneToMany(mappedBy = "poll", orphanRemoval = true)
    private List<PollQuestion> questions;

    @ManyToOne
    private Initiative initiative;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Participant> participants;

    public Poll() {
    }

    public Poll(String topic, boolean isOpen, List<PollQuestion> questions, Initiative initiative,
                List<Participant> participants) {
        this.topic = topic;
        this.isOpen = isOpen;
        this.questions = questions;
        this.initiative = initiative;
        this.participants = participants;
    }

    public Poll(String topic, boolean isOpen, List<PollQuestion> questions, Initiative initiative) {
        this.topic = topic;
        this.isOpen = isOpen;
        this.questions = questions;
        this.initiative = initiative;
        this.participants = new ArrayList<>();
    }

    public Poll(String topic, boolean isOpen, Initiative initiative) {
        this.topic = topic;
        this.isOpen = isOpen;
        this.questions = new ArrayList<>();
        this.initiative = initiative;
        this.participants = new ArrayList<>();
    }
}
