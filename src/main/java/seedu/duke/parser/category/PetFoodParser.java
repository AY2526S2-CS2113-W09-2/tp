package seedu.duke.parser.category;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.FieldParser;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses pet food-specific fields from user input.
 */
public class PetFoodParser {
    private static final Logger logger = Logger.getLogger(PetFoodParser.class.getName());

    public final String petType;
    public final String brand;

    /**
     * Creates a {@code PetFoodParser} object with the parsed pet food details.
     *
     * @param petType Type of pet the food is for.
     * @param brand Brand of the pet food.
     */
    public PetFoodParser(String petType, String brand) {
        this.petType = petType;
        this.brand = brand;
    }

    /**
     * Parses the pet food-related fields from the given input string.
     *
     * @param input User input containing pet food fields.
     * @return A {@code PetFoodParser} containing the parsed values.
     * @throws DukeException If any required field is missing or invalid.
     */
    public static PetFoodParser parse(String input) throws DukeException {
        assert input != null : "PetFoodParser received null input.";
        logger.log(Level.INFO, "Processing PetFood special fields.");

        String petType = FieldParser.extractField(input, "petType/", "brand/");
        if (petType == null || petType.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing petType for pet food.");
            throw new DukeException("Missing petType for pet food.");
        }

        String brand = FieldParser.extractField(input, "brand/", null);
        if (brand == null || brand.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing brand for pet food.");
            throw new DukeException("Missing brand for pet food.");
        }

        logger.log(Level.INFO, "End of processing pet food.");
        return new PetFoodParser(petType, brand);
    }
}
