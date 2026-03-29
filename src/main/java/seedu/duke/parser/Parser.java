package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.UI;

/**
 * Parses user input into corresponding Command objects.
 */
public class Parser {

    private final UI ui;

    /**
     * Creates a Parser with the specified UI instance.
     *
     * @param ui The UI used to display messages to the user.
     */
    public Parser(UI ui) {
        this.ui = ui;
    }

    /**
     * Parses a raw user input string into a corresponding Command.
     *
     * @param input The raw input string entered by the user.
     * @return The parsed Command, or {@code null} if input is invalid.
     * @throws DukeException If parsing fails due to invalid format.
     */
    public Command parse(String input) throws DukeException {
        assert input != null : "Parser received null input.";

        String trimmed = input.trim();

        if (trimmed.isEmpty()) {
            ui.showEmptyInput();
            return null;
        }

        String[] parts = trimmed.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        switch (commandWord) {
        case "add":
            return new AddCommandParser(ui).parse(arguments);
        case "delete":
            return new DeleteCommandParser(ui).parse(arguments);
        case "update":
            return new UpdateCommandParser(ui).parse(arguments);
        case "find":
            return new FindItemParser(ui).parse(arguments);
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        default:
            ui.showUnknownCommand();
            return null;
        }
    }
}
