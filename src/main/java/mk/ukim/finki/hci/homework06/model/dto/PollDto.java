package mk.ukim.finki.hci.homework06.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PollDto {

    private List<OpenQuestionDto> open;
    private List<Long> single;
    private List<Long> multiple;

    public PollDto() {
        open = new ArrayList<>();
        single = new ArrayList<>();
        multiple = new ArrayList<>();
    }

    public PollDto(List<OpenQuestionDto> open, List<Long> single, List<Long> multiple) {
        this.open = open;
        this.single = single;
        this.multiple = multiple;
    }
}
