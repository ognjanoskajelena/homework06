package mk.ukim.finki.hci.homework06.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDate datePosted;

    private LocalTime timePosted;

    @ManyToOne
    private User author;

    @JsonIgnore
    @ManyToOne
    private Discussion discussion;

    public Comment() {
    }

    public Comment(String content, User author, Discussion discussion) {
        this.content = content;
        this.datePosted = LocalDate.now();
        this.timePosted = LocalTime.now();
        this.author = author;
        this.discussion = discussion;
    }
}
