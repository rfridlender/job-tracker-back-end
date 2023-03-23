package app.door2door.jobtracker.exceptions;

public class EmailTakenException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public EmailTakenException(String message) {
        super(message);
    }

}
