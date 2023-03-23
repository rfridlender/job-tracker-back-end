package app.door2door.jobtracker.exceptions;

public class IncorrectPasswordException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public IncorrectPasswordException(String message) {
        super(message);
    }

}
