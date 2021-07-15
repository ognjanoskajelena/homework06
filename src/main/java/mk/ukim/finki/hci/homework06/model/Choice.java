package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private boolean selected;

    @ManyToOne
    private PollQuestion question;

    public Choice() {
        this.selected = false;
    }

    public Choice(String content, boolean selected) {
        this.content = content;
        this.selected = selected;
    }

    public Choice(String content) {
        this.content = content;
        this.selected = false;
    }
}
