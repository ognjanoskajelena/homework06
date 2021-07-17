package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SingleChoiceQuestion extends PollQuestion {

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<Choice> choices;

    public SingleChoiceQuestion() {
        super();
        this.choices = new ArrayList<>();
    }

    public SingleChoiceQuestion(String content, Poll poll) {
        super(content, PollQuestionType.SINGLE, poll);
        this.choices = new ArrayList<>();
    }

    public SingleChoiceQuestion(String content, Poll poll, List<Choice> choices) {
        super(content, PollQuestionType.SINGLE, poll);
        this.choices = choices;
    }

    public void addToChoices(Choice choice) {
        this.choices.add(choice);
    }
}
