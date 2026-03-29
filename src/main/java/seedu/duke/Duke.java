package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.logging.LoggerConfig;
import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

/**
 * Represents the entry point of the Inventory Management application.
 * Initializes core components such as UI, Parser, Storage, and Inventory.
 * Manages the main execution loop of the application.
 */
public class Duke {
    private final Inventory inventory;
    private final UI ui;
    private final Parser parser;
    private final Storage storage;

    /**
     * Constructs the Duke application by initializing UI, parser, storage,
     * and pre-defined categories, then loads stored data.
     *
     * @throws DukeException If loading stored data fails.
     */
    public Duke() throws DukeException {
        ui = new UI();
        inventory = new Inventory();
        parser = new Parser(ui);
        storage = new Storage("./data/inventory.txt");

        String[] categoryNames = {
            "fruits",
            "vegetables",
            "toiletries",
            "snacks",
            "drinks",
            "icecream",
            "sweets",
            "burger",
            "setmeal",
            "seafood",
            "meat",
            "petfood",
            "accessories"
        };

        for (String categoryName : categoryNames) {
            inventory.addCategory(new Category(categoryName));
        }

        storage.load(inventory, ui);
    }

    /**
     * Starts the application by setting up logging and launching the main program loop.
     *
     * @param args Command-line arguments (not used).
     * @throws DukeException If initialization fails.
     */
    public static void main(String[] args) throws DukeException {
        LoggerConfig logger = new LoggerConfig("./logs/logger.txt");
        logger.setup();
        new Duke().run();
    }

    /**
     * Runs the main command loop of the application.
     * Continuously reads user input, parses commands, executes them,
     * and persists changes until the exit command is issued.
     */
    public void run() {
        ui.showWelcome();

        String input;
        while ((input = ui.readCommand()) != null) {
            try {
                Command command = parser.parse(input);

                if (command == null) {
                    continue;
                }

                if (command.isExit()) {
                    storage.save(inventory);
                    break;
                }

                command.execute(inventory, ui);
                storage.save(inventory);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

        ui.showGoodbye();
        ui.close();
    }
}
