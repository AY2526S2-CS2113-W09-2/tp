package seedu.duke.command;

import seedu.duke.model.Inventory;
import seedu.duke.ui.UI;

/**
 * Represents a command that ends the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * No action is needed here because exiting is handled by {@link #isExit()}.
     * Exit handled by isExit() in main loop
     *
     * @param inventory Inventory instance, unused.
     * @param ui UI instance, unused.
     */
    @Override
    public void execute(Inventory inventory, UI ui) {

    }

    /**
     * Returns {@code true} to indicate that the program should terminate.
     *
     * @return {@code true}.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
