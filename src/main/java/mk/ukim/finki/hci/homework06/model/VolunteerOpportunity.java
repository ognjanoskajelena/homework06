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

    private String description;

    private LocalDate datePosted;

    private boolean isActive;

    @ManyToOne
    private Administrator administrator;

    public VolunteerOpportunity() {
    }

    public VolunteerOpportunity(String topic, String description, Administrator administrator) {
        this.topic = topic;
        this.description = description;
        this.datePosted = LocalDate.now();
        this.administrator = administrator;
        this.isActive = true;
    }

    public VolunteerOpportunity(String topic, String description, boolean isActive, Administrator administrator) {
        this.topic = topic;
        this.description = description;
        this.datePosted = LocalDate.now();
        this.isActive = isActive;
        this.administrator = administrator;
    }
}
