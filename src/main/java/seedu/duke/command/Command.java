package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Inventory;
import seedu.duke.ui.UI;

/**
 * Represents an executable user command in the inventory system.
 * Each concrete command defines how it modifies or queries the inventory.
 */
public abstract class Command {

    /**
     * Executes the command using the given inventory and user interface.
     *
     * @param inventory Inventory used by the command.
     * @param ui UI used to display output to the user.
     * @throws DukeException If execution fails due to invalid data or command conditions.
     */
    public abstract void execute(Inventory inventory, UI ui) throws DukeException;

    /**
     * Returns whether this command causes the program to exit.
     *
     * @return {@code true} if this is an exit command, otherwise {@code false}.
     */
    public boolean isExit() {
        return false;
    }
}
