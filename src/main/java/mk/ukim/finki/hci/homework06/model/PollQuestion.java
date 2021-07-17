package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import javax.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PollQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Enumerated(EnumType.STRING)
    private PollQuestionType type;

    @ManyToOne
    private Poll poll;

    public PollQuestion() {
    }

    public PollQuestion(String content, PollQuestionType type, Poll poll) {
        this.content = content;
        this.type = type;
        this.poll = poll;
    }

    public abstract void addToChoices(Choice choice);
}
