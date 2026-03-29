package seedu.duke.command;

import seedu.duke.model.Inventory;
import seedu.duke.ui.UI;

/**
 * Displays the list of supported commands to the user.
 */
public class HelpCommand extends Command {

    /**
     * Shows the help menu through the UI.
     *
     * @param inventory Inventory instance, unused.
     * @param ui UI used to display the help message.
     */
    @Override
    public void execute(Inventory inventory, UI ui) {
        ui.showHelp();
    }
}
