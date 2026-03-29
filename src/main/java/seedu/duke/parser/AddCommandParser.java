package seedu.duke.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.UI;

/**
 * Parses add command input into the corresponding command object.
 */
public class AddCommandParser {
    private static final Logger logger = Logger.getLogger(AddCommandParser.class.getName());

    private final UI ui;

    /**
     * Creates a parser that uses the given UI to display feedback messages.
     *
     * @param ui UI used to show parsing-related messages.
     */
    public AddCommandParser(UI ui) {
        assert ui != null : "AddCommandParser received null UI.";
        this.ui = ui;
    }

    /**
     * Parses the given add command input and returns the corresponding command.
     *
     * @param input Full user input for the add command.
     * @return The parsed command for the specified category.
     * @throws DukeException If the input is empty, missing required fields,
     *                       or contains an unknown category.
     */
    public Command parse(String input) throws DukeException {
        assert input != null : "AddCommandParser received null input.";
        if (input.isEmpty()) {
            logger.log(Level.WARNING, "Add command input is empty.");
            throw new DukeException("Input is empty.");
        }

        String trimmedInput = input.trim();
        validateRequiredFields(trimmedInput);

        String category = extractCategory(trimmedInput);
        logger.log(Level.INFO, "Processing add command for category: " + category);
        return parseByCategory(trimmedInput, category);
    }

    /**
     * Validates that the required fields for an add command are present.
     *
     * @param input User input to validate.
     * @throws DukeException If the item name or category is missing.
     */
    private void validateRequiredFields(String input) throws DukeException {
        String itemName = extractFieldValue(input, "item/");
        if (itemName == null || itemName.isEmpty()) {
            logger.log(Level.WARNING, "Missing item name in add command.");
            throw new DukeException("Missing item name.");
        }

        String category = extractFieldValue(input, "category/");
        if (category == null || category.isEmpty()) {
            logger.log(Level.WARNING, "Missing category in add command.");
            throw new DukeException("Missing category.");
        }
    }

    /**
     * Extracts the category field from the input in lowercase.
     *
     * @param input User input containing a category field.
     * @return The category in lowercase.
     */
    private String extractCategory(String input) {
        return extractFieldValue(input, "category/").toLowerCase();
    }

    /**
     * Extracts the value associated with the given prefix from the input.
     *
     * @param input User input to search.
     * @param prefix Field prefix to match, such as {@code item/} or {@code category/}.
     * @return The extracted field value, or {@code null} if the prefix is not found.
     */
    private String extractFieldValue(String input, String prefix) {
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            if (token.startsWith(prefix)) {
                return token.substring(prefix.length()).trim();
            }
        }
        return null;
    }

    /**
     * Parses the input according to the given category and delegates
     * the parsing to the relevant handler.
     *
     * @param input Full user input for the add command.
     * @param category Category of item to be added.
     * @return The parsed command corresponding to the category.
     * @throws DukeException If the category is unknown or parsing fails.
     */
    private Command parseByCategory(String input, String category) throws DukeException {
        AddItemCommandParser parser = new AddItemCommandParser();

        switch (category) {
        case "fruits":
            return parser.handleFruit(input);
        case "snacks":
            return parser.handleSnack(input);
        case "toiletries":
            return parser.handleToiletries(input);
        case "vegetables":
            return parser.handleVegetables(input);
        case "drinks":
            return parser.handleDrinks(input);
        case "icecream":
            return parser.handleIceCream(input);
        case "sweets":
            return parser.handleSweets(input);
        case "burger":
            return parser.handleBurger(input);
        case "setmeal":
            return parser.handleSetMeal(input);
        case "seafood":
            return parser.handleSeafood(input);
        case "meat":
            return parser.handleMeat(input);
        case "petfood":
            return parser.handlePetFood(input);
        case "accessories":
            return parser.handleAccessories(input);
        default:
            logger.log(Level.WARNING, "Unknown add command category: " + category);
            throw new DukeException("Unknown category: " + category);
        }
    }
}
