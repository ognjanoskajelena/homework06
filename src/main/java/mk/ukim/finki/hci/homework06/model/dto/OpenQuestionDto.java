package mk.ukim.finki.hci.homework06.model.dto;

import lombok.Data;

@Data
public class OpenQuestionDto {

    private Long id;
    private String response;

    public OpenQuestionDto() {
    }

    public OpenQuestionDto(Long id, String response) {
        this.id = id;
        this.response = response;
    }
}
