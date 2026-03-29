package seedu.duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.model.Item;
import seedu.duke.parser.AddItemCommandParser;
import seedu.duke.ui.UI;

/**
 * Handles loading and saving of inventory data to persistent storage.
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Storage.class.getName());
    private final File dataFile;

    /**
     * Constructs a Storage object for the given file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        assert filePath != null : "Storage received null file path.";
        this.dataFile = new File(filePath);
    }

    /**
     * Creates the storage file and its parent directories if they do not already exist.
     *
     * @throws IOException If the file or its parent directories cannot be created.
     */
    private void createFile() throws IOException {
        if (dataFile.exists()) {
            return;
        }
        File parent = dataFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        dataFile.createNewFile();
    }

    /**
     * Loads inventory data from the storage file into the given inventory.
     * Corrupted lines are skipped and reported to the user.
     *
     * @param inventory The inventory to populate.
     * @param ui        The UI used to display messages.
     * @throws DukeException If the file cannot be read.
     */
    public void load(Inventory inventory, UI ui) throws DukeException {
        assert inventory != null : "Storage.load received null inventory.";
        assert ui != null : "Storage.load received null ui.";
        try {
            createFile();
            List<String> lines = Files.readAllLines(dataFile.toPath());
            AddItemCommandParser addItemCommandParser = new AddItemCommandParser();

            for (String line : lines) {
                try {
                    Command command = parseStoredLine(line, addItemCommandParser);
                    command.execute(inventory, null);
                } catch (DukeException e) {
                    logger.log(Level.WARNING, "Skipped corrupted line: " + line
                            + " Reason: " + e.getMessage());
                    ui.showSkippedLine(line, e.getMessage());
                }
            }
            logger.log(Level.INFO, "Finished loading inventory.");
        } catch (IOException e) {
            throw new DukeException("Unable to read storage file.");
        }
    }

    /**
     * Parses a single line from storage into a Command.
     *
     * @param line   The line from the storage file.
     * @param parser The parser used to interpret item data.
     * @return The corresponding Command.
     * @throws DukeException If parsing fails.
     */
    private Command parseStoredLine(String line, AddItemCommandParser parser) throws DukeException {
        assert line != null : "Storage.parseStoredLine received null line.";
        assert parser != null : "Storage.parseStoredLine received null parser.";
        String category = extractCategory(line);

        switch (category) {
        case "fruits":
            return parser.handleFruit(line);
        case "snacks":
            return parser.handleSnack(line);
        case "toiletries":
            return parser.handleToiletries(line);
        case "vegetables":
            return parser.handleVegetables(line);
        case "drinks":
            return parser.handleDrinks(line);
        case "icecream":
            return parser.handleIceCream(line);
        case "sweets":
            return parser.handleSweets(line);
        case "burger":
            return parser.handleBurger(line);
        case "setmeal":
            return parser.handleSetMeal(line);
        case "seafood":
            return parser.handleSeafood(line);
        case "meat":
            return parser.handleMeat(line);
        case "petfood":
            return parser.handlePetFood(line);
        case "accessories":
            return parser.handleAccessories(line);
        default:
            throw new DukeException("Unknown category in storage.");
        }
    }

    /**
     * Extracts the category name from a storage line.
     *
     * @param line The storage line.
     * @return The extracted category name.
     * @throws DukeException If category is missing.
     */
    private String extractCategory(String line) throws DukeException {
        assert line != null : "Storage.extractCategory received null line.";
        String[] tokens = line.trim().split(" ");

        for (String token : tokens) {
            if (token.startsWith("category/")) {
                return token.substring("category/".length()).trim().toLowerCase();
            }
        }

        throw new DukeException("Missing category in storage line.");
    }

    /**
     * Saves the current inventory state to the storage file.
     *
     * @param inventory The inventory to save.
     * @throws DukeException If saving fails.
     */
    public void save(Inventory inventory) throws DukeException {
        assert inventory != null : "Storage.save received null inventory.";
        List<String> lines = new ArrayList<>();

        for (Category category : inventory.getCategories()) {
            for (Item item : category.getItems()) {
                lines.add(formatItem(item, category.getName()));
            }
        }

        try {
            createFile();
            Files.write(dataFile.toPath(), lines);
            logger.log(Level.INFO, "Saved inventory to storage file.");
        } catch (IOException e) {
            throw new DukeException("Unable to save inventory.");
        }
    }

    /**
     * Formats an item into a string for storage.
     *
     * @param item         The item to format.
     * @param categoryName The category of the item.
     * @return The formatted storage string.
     */
    private String formatItem(Item item, String categoryName) {
        assert item != null : "Storage.formatItem received null item.";
        assert categoryName != null : "Storage.formatItem received null categoryName.";
        return item.toStorageString(categoryName);
    }
}
