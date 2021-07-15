package mk.ukim.finki.hci.homework06.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class VolunteerOpportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private String description;

    @ManyToOne
    private Administrator administrator;

    public VolunteerOpportunity() {
    }

    public VolunteerOpportunity(String topic, String description, Administrator administrator) {
        this.topic = topic;
        this.description = description;
        this.administrator = administrator;
    }
}
