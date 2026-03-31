package seedu.duke.command;

import seedu.duke.model.Inventory;
import seedu.duke.ui.UI;

/**
 * Represents a command to display the help message,
 * directing the user to the User Guide.
 */
public class HelpCommand extends Command {

    @Override
    public void execute(Inventory inventory, UI ui) {
        ui.showHelp();
    }
}
