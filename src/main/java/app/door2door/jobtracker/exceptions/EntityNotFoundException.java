package app.door2door.jobtracker.exceptions;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public EntityNotFoundException(String message) {
        super(message);
    }

}