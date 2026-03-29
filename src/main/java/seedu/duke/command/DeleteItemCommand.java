package seedu.duke.command;

import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.model.Item;
import seedu.duke.ui.UI;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deletes an item by name from the inventory.
 * The command searches through all categories until a match is found.
 */
public class DeleteItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteItemCommand.class.getName());

    private final String itemName;

    /**
     * Creates a delete-item command for the specified item name.
     *
     * @param itemName Name of the item to delete.
     */
    public DeleteItemCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Removes the matching item from its category if found.
     *
     * @param inventory Inventory to search through.
     * @param ui UI used to display the result.
     */
    @Override
    public void execute(Inventory inventory, UI ui) {
        assert inventory != null : "DeleteItemCommand received null inventory.";
        assert ui != null : "DeleteItemCommand received null UI.";
        assert itemName != null : "DeleteItemCommand received null item name.";
        List<Category> categories = inventory.getCategories();

        for (Category category : categories) {
            Item item = category.findItemByName(itemName);
            if (item != null) {
                category.getItems().remove(item);
                logger.log(Level.INFO, "Deleted item '" + itemName
                        + "' from category '" + category.getName() + "'.");
                ui.showItemDeleted(itemName,
                        category.getName());
                return;
            }
        }

        logger.log(Level.WARNING, "Item not found for deletion: " + itemName);
        ui.showItemNotFound(itemName);
    }
}
