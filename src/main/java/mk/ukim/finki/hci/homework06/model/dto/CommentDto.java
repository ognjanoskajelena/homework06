package mk.ukim.finki.hci.homework06.model.dto;

import lombok.Data;

@Data
public class CommentDto {

    private String content;

    private String author;

    public CommentDto() {
    }

    public CommentDto(String content, String author) {
        this.content = content;
        this.author = author;
    }
}
