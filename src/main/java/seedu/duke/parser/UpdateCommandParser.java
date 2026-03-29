package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.UpdateItemCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.UI;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses update command input into the corresponding command object.
 */
public class UpdateCommandParser {
    private final UI ui;

    /**
     * Creates a parser that uses the given UI to display feedback messages.
     *
     * @param ui UI used to show parsing-related messages.
     */
    public UpdateCommandParser(UI ui) {
        this.ui = ui;
    }

    /**
     * Parses the given update command input and returns the corresponding command.
     *
     * @param input User input specifying the category, item index, and fields to update.
     * @return The parsed update command, or {@code null} if the input is empty.
     * @throws DukeException If required fields are missing, the item index is invalid,
     *                       or the update format is incorrect.
     */
    public Command parse(String input) throws DukeException {
        assert input != null : "UpdateCommandParser received null input.";

        if (input.isEmpty()) {
            ui.showInvalidInput("Use: update category/CATEGORY index/INDEX "
                    + "[newItem/NAME] [bin/BIN] [qty/QTY] [expiryDate/DATE] ...");
            return null;
        }

        String[] tokens = input.trim().split("\\s+");
        Map<String, String> fields = new HashMap<>();

        for (String token : tokens) {
            int separatorIndex = token.indexOf('/');
            if (separatorIndex <= 0 || separatorIndex == token.length() - 1) {
                throw new DukeException("Invalid update token: " + token);
            }

            String key = token.substring(0, separatorIndex);
            String value = token.substring(separatorIndex + 1);
            fields.put(key, value);
        }

        String categoryName = fields.remove("category");
        if (categoryName == null || categoryName.trim().isEmpty()) {
            throw new DukeException("Missing category.");
        }

        String itemIndexString = fields.remove("index");
        if (itemIndexString == null || itemIndexString.trim().isEmpty()) {
            throw new DukeException("Missing item index.");
        }

        int itemIndex;
        try {
            itemIndex = Integer.parseInt(itemIndexString.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Item index must be an integer.");
        }

        if (itemIndex <= 0) {
            throw new DukeException("Item index must be a positive integer.");
        }

        if (fields.isEmpty()) {
            throw new DukeException("Provide at least one field to update.");
        }

        return new UpdateItemCommand(categoryName.trim(),
                itemIndex, fields);
    }
}
