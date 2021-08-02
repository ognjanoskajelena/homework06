package mk.ukim.finki.hci.homework06.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Transient
    private boolean isOpen;

    @JsonIgnore
    @ManyToOne
    private Initiative initiative;

    @OneToMany(mappedBy = "discussion", orphanRemoval = true)
    private List<Comment> comments;

    public Discussion() {
        this.isOpen = true;
    }

    public Discussion(String topic, LocalDate closeDate, Initiative initiative) {
        this.topic = topic;
        this.closeDate = closeDate;
        this.isOpen = true;
        this.initiative = initiative;
    }

    public void close() {
        this.isOpen = false;
    }
}
