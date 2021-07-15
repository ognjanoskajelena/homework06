package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MultipleChoiceQuestion extends PollQuestion {

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<Choice> choices;

    public MultipleChoiceQuestion() {
        super();
        this.choices = new ArrayList<>();
    }

    public MultipleChoiceQuestion(String content, Poll poll, List<Choice> choices) {
        super(content, PollQuestionType.MULTIPLE, poll);
        this.choices = choices;
    }
}
