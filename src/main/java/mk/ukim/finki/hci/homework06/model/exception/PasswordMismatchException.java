package mk.ukim.finki.hci.homework06.model.exception;

public class PasswordMismatchException extends RuntimeException {

    public PasswordMismatchException() {
        super("Password mismatch!");
    }
}
