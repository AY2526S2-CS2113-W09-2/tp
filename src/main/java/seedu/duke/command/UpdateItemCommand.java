package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.model.Item;
import seedu.duke.parser.category.CommonFieldParser;
import seedu.duke.ui.UI;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Updates selected fields of an existing item in a category.
 */
public class UpdateItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(UpdateItemCommand.class.getName());

    private final String categoryName;
    private final int itemIndex;
    private final Map<String, String> updates;

    /**
     * Creates an update-item command with the target category, item index, and updated fields.
     *
     * @param categoryName Name of the category containing the item.
     * @param itemIndex One-based index of the item in the category.
     * @param updates Map of field names to new values.
     */
    public UpdateItemCommand(String categoryName, int itemIndex,
                             Map<String, String> updates) {
        this.categoryName = categoryName;
        this.itemIndex = itemIndex;
        this.updates = updates;
    }

    /**
     * Updates the specified item with the provided field values.
     *
     * @param inventory Inventory containing the target category and item.
     * @param ui UI used to display confirmation messages.
     * @throws DukeException If the category or item index is invalid, or any update value is invalid.
     */
    @Override
    public void execute(Inventory inventory, UI ui) throws DukeException {
        assert inventory != null : "UpdateItemCommand received null inventory.";
        assert ui != null : "UpdateItemCommand received null UI.";

        Category category = inventory.findCategoryByName(categoryName);
        if (category == null) {
            logger.log(Level.WARNING, "Category not found while updating item: " + categoryName);
            throw new DukeException("Category not found: " + categoryName);
        }

        if (itemIndex < 1 || itemIndex > category.getItemCount()) {
            logger.log(Level.WARNING, "Invalid item index while updating item: " + itemIndex);
            throw new DukeException("Invalid item index: " + itemIndex);
        }

        Item item = category.getItem(itemIndex - 1);
        String originalName = item.getName();
        applyUpdates(item);
        logger.log(Level.INFO, "Updated item '" + originalName
                + "' in category '" + category.getName()
                + "' to '" + item.getName() + "'.");
        ui.showItemUpdated(originalName, item.getName(), category.getName());
    }

    /**
     * Applies all requested field updates to the given item.
     *
     * @param item Item to be updated.
     * @throws DukeException If an update field is unsupported or a value is invalid.
     */
    private void applyUpdates(Item item) throws DukeException {
        for (Map.Entry<String, String> entry : updates.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();

            switch (field) {
            case "newItem":
                validateNonEmpty(value, "New item name cannot be empty.");
                item.setName(value.trim());
                break;
            case "bin":
                validateNonEmpty(value, "Bin location cannot be empty.");
                item.setBinLocation(value.trim());
                break;
            case "qty":
                item.setQuantity(CommonFieldParser.parseQuantity(value));
                break;
            case "expiryDate":
                CommonFieldParser.validateExpiryDate(value);
                item.setExpiryDate(value.trim());
                break;
            default:
                throw new DukeException("Only newItem/, bin/, qty/, and expiryDate/ can be updated.");
            }
        }
    }

    /**
     * Ensures that the given string is not null or blank.
     *
     * @param value Value to validate.
     * @param message Exception message to use if validation fails.
     * @throws DukeException If the value is null or blank.
     */
    private void validateNonEmpty(String value,
                                  String message) throws DukeException {
        if (value == null || value.trim().isEmpty()) {
            throw new DukeException(message);
        }
    }
}
