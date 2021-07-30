package mk.ukim.finki.hci.homework06.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PollDto {

    private String username;
    private List<OpenQuestionDto> open;
    private List<Long> single;
    private List<Long> multiple;

    public PollDto() {
        open = new ArrayList<>();
        single = new ArrayList<>();
        multiple = new ArrayList<>();
    }

    public PollDto(String username, List<OpenQuestionDto> open, List<Long> single, List<Long> multiple) {
        this.username = username;
        this.open = open;
        this.single = single;
        this.multiple = multiple;
    }
}
