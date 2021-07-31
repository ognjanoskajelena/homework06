package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class VolunteerOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    @Column(length = 1024)
    private String description;

    private LocalDate datePosted;

    private boolean isActive;

    @ManyToOne
    private User author;

    public VolunteerOpportunity() {
    }

    public VolunteerOpportunity(String topic, String description, User author) {
        this.topic = topic;
        this.description = description;
        this.datePosted = LocalDate.now();
        this.author = author;
        this.isActive = true;
    }
}
