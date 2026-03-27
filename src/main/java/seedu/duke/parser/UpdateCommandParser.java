package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.UpdateItemCommand;
import seedu.duke.exception.DukeException;
import seedu.duke.ui.UI;

import java.util.HashMap;
import java.util.Map;

public class UpdateCommandParser {
    private final UI ui;

    public UpdateCommandParser(UI ui) {
        this.ui = ui;
    }

    public Command parse(String input) throws DukeException {
        assert input != null : "UpdateCommandParser received null input.";

        if (input.isEmpty()) {
            ui.showInvalidInput("Use: update category/CATEGORY item/ITEM "
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

        String itemName = fields.remove("item");
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new DukeException("Missing item name.");
        }

        if (fields.isEmpty()) {
            throw new DukeException("Provide at least one field to update.");
        }

        return new UpdateItemCommand(categoryName.trim(),
                itemName.trim(), fields);
    }
}
