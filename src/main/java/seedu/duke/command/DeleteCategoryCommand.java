package seedu.duke.command;

import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.ui.UI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Deletes a category from the inventory.
 * If the category contains items, user confirmation is requested first.
 */
public class DeleteCategoryCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCategoryCommand.class.getName());

    private final String categoryName;

    /**
     * Creates a delete-category command for the specified category.
     *
     * @param categoryName Name of the category to delete.
     */
    public DeleteCategoryCommand(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Deletes the specified category after optional confirmation when it is not empty.
     *
     * @param inventory Inventory containing the category.
     * @param ui UI used to show messages and read confirmation input.
     */
    @Override
    public void execute(Inventory inventory, UI ui) {
        assert inventory != null : "DeleteCategoryCommand received null inventory.";
        assert ui != null : "DeleteCategoryCommand received null UI.";
        assert categoryName != null : "DeleteCategoryCommand received null category name.";
        Category category = inventory.findCategoryByName(
                categoryName);

        if (category == null) {
            logger.log(Level.WARNING, "Category not found for deletion: " + categoryName);
            ui.showCategoryNotFound(categoryName);
            return;
        }

        if (!category.isEmpty()) {
            ui.showDeleteCategoryConfirmation(
                    categoryName, category.getItemCount());
            String response = ui.readCommand();

            if (response == null
                    || !response.trim()
                    .equalsIgnoreCase("yes")) {
                logger.log(Level.INFO, "Category deletion cancelled for: " + categoryName);
                ui.showDeleteCategoryCancelled(categoryName);
                return;
            }

            category.getItems().clear();
            ui.showCategoryItemsCleared(categoryName);
        }
        logger.log(Level.INFO, "Deleted category '" + categoryName + "'.");
        ui.showCategoryDeleted(categoryName);
    }
}
