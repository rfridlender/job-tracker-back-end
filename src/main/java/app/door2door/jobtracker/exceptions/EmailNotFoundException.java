package app.door2door.jobtracker.exceptions;

import java.io.Serial;

public class EmailNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public EmailNotFoundException(String message) {
        super(message);
    }

}
