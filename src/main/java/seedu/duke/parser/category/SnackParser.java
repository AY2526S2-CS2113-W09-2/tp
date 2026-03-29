package seedu.duke.parser.category;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.FieldParser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses snack-specific fields from user input.
 */
public class SnackParser {
    private static final Logger logger = Logger.getLogger(SnackParser.class.getName());
    public final String brand;

    /**
     * Creates a {@code SnackParser} object with the parsed snack details.
     *
     * @param brand Brand of the snack.
     */
    public SnackParser(String brand) {
        this.brand = brand;
    }

    /**
     * Parses the snack-related fields from the given input string.
     *
     * @param input User input containing snack fields.
     * @return A {@code SnackParser} containing the parsed values.
     * @throws DukeException If the required field is missing or invalid.
     */
    public static SnackParser parse(String input) throws DukeException {
        assert input != null : "SnackParser received null inputs.";
        String brand = FieldParser.extractField(input, "brand/", null);
        if (brand == null || brand.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing brand for snack.");
            throw new DukeException("Missing brand for snack.");
        }

        return new SnackParser(brand);
    }
}

