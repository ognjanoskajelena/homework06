package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private LocalDate closeDate;

    @ManyToOne
    private Initiative initiative;

    @OneToMany(mappedBy = "discussion", orphanRemoval = true)
    private List<Comment> comments;

    public Discussion() {
    }

    public Discussion(String topic, LocalDate closeDate, Initiative initiative) {
        this.topic = topic;
        this.closeDate = closeDate;
        this.initiative = initiative;
    }

    public void addToComments(Comment comment) {
        this.comments.add(comment);
    }
}
