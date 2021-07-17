package mk.ukim.finki.hci.homework06.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ChoiceNotFoundException extends RuntimeException {

    public ChoiceNotFoundException(Long id) {
        super(String.format("Choice with id %d is not found!", id));
    }
}
