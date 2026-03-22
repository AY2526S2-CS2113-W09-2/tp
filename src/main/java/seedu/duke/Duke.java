package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.UI;

import java.io.IOException;

public class Duke {
    private final Inventory inventory;
    private final UI ui;
    private final Parser parser;
    private final Storage storage;

    public Duke() throws DukeException {
        ui = new UI();
        inventory = new Inventory();
        parser = new Parser(ui);

        try {
            storage = new Storage("./data/inventory.txt");
        } catch (IOException e) {
            throw new DukeException("Unable to initialize storage file.");
        }

        inventory.addCategories(new Category("fruits"));
        inventory.addCategories(new Category("vegetables"));
        inventory.addCategories(new Category("toiletries"));
        inventory.addCategories(new Category("snacks"));

        storage.load(inventory, ui);
    }

    public void run() throws DukeException {
        ui.showWelcome();

        String input;
        while ((input = ui.readCommand()) != null) {
            try {
                Command command = parser.parse(input);

                if (command == null) {
                    continue;
                }

                if (command.isExit()) {
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

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }
}
