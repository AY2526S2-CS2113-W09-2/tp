package seedu.duke.parser.category;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.FieldParser;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses sweets-specific fields from user input.
 */
public class SweetsParser {
    private static final Logger logger = Logger.getLogger(SweetsParser.class.getName());

    public final String brand;
    public final String sweetnessLevel;

    /**
     * Creates a {@code SweetsParser} object with the parsed sweets details.
     *
     * @param brand Brand of the sweets.
     * @param sweetnessLevel Sweetness level of the sweets.
     */
    public SweetsParser(String brand, String sweetnessLevel) {
        this.brand = brand;
        this.sweetnessLevel = sweetnessLevel;
    }

    /**
     * Parses the sweets-related fields from the given input string.
     *
     * @param input User input containing sweets fields.
     * @return A {@code SweetsParser} containing the parsed values.
     * @throws DukeException If any required field is missing or invalid.
     */
    public static SweetsParser parse(String input) throws DukeException {
        assert input != null : "SweetsParser received null input.";
        logger.log(Level.INFO, "Processing Sweets special fields.");

        String brand = FieldParser.extractField(input, "brand/", "sweetnessLevel/");
        if (brand == null || brand.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing brand for sweets.");
            throw new DukeException("Missing brand for sweets.");
        }

        String sweetnessLevel = FieldParser.extractField(input, "sweetnessLevel/", null);
        if (sweetnessLevel == null || sweetnessLevel.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing sweetness level for sweets.");
            throw new DukeException("Missing sweetness level for sweets.");
        }

        logger.log(Level.INFO, "End of processing sweets.");
        return new SweetsParser(brand, sweetnessLevel);
    }
}
