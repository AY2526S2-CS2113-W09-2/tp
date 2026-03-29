package seedu.duke.exception;

/**
 * Represents an exception specific to the Duke application.
 * It is used to signal command, parsing, or validation errors
 * that should be shown to the user.
 */
public class DukeException extends Exception {

    /**
     * Creates a Duke exception with the specified error message.
     *
     * @param message Message describing the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
