package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class OpenQuestion extends PollQuestion {

    private String response;

    public OpenQuestion() {
        super();
    }

    public OpenQuestion(String content, Poll poll) {
        super(content, PollQuestionType.OPEN, poll);
    }

    public void respond(String response) {
        this.response = response;
    }

    public void getChoices() {

    }
}
