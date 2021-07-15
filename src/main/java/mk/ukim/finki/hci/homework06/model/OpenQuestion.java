package mk.ukim.finki.hci.homework06.model;

import lombok.Data;
import mk.ukim.finki.hci.homework06.model.enums.PollQuestionType;

import javax.persistence.Entity;

@Data
@Entity
public class OpenQuestion extends PollQuestion {

    private String response;

    public OpenQuestion() {
        super();
    }

    public OpenQuestion(String content, Poll poll, String response) {
        super(content, PollQuestionType.OPEN, poll);
        this.response = response;
    }
}
