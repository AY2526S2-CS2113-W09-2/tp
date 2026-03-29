package seedu.duke.parser.category;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.FieldParser;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Parses set meal-specific fields from user input.
 */
public class SetMealParser {
    private static final Logger logger = Logger.getLogger(SetMealParser.class.getName());

    public final String mealType;
    public final String foodSize;

    /**
     * Creates a {@code SetMealParser} object with the parsed set meal details.
     *
     * @param mealType Type of the set meal.
     * @param foodSize Food size of the set meal.
     */
    public SetMealParser(String mealType, String foodSize) {
        this.mealType = mealType;
        this.foodSize = foodSize;
    }

    /**
     * Parses the set meal-related fields from the given input string.
     *
     * @param input User input containing set meal fields.
     * @return A {@code SetMealParser} containing the parsed values.
     * @throws DukeException If any required field is missing or invalid.
     */
    public static SetMealParser parse(String input) throws DukeException {
        assert input != null : "SetMealParser received null input.";
        logger.log(Level.INFO, "Processing SetMeal special fields.");

        String mealType = FieldParser.extractField(input, "mealType/", "foodSize/");
        if (mealType == null || mealType.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing mealType for set meal.");
            throw new DukeException("Missing mealType for set meal.");
        }

        String foodSize = FieldParser.extractField(input, "foodSize/", null);
        if (foodSize == null || foodSize.trim().isEmpty()) {
            logger.log(Level.WARNING, "Missing foodSize for set meal.");
            throw new DukeException("Missing foodSize for set meal.");
        }

        logger.log(Level.INFO, "End of processing set meal.");
        return new SetMealParser(mealType, foodSize);
    }
}
