package seedu.duke.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the full inventory containing multiple categories.
 */
public class Inventory {
    private List<Category> inventory;

    /**
     * Creates an empty inventory.
     */
    public Inventory() {
        inventory = new ArrayList<>();
    }

    /**
     * Adds a category to the inventory.
     *
     * @param category Category to add.
     */
    public void addCategory(Category category) {
        inventory.add(category);
    }

    /**
     * Returns all categories in the inventory.
     *
     * @return List of categories.
     */
    public List<Category> getCategories() {
        return inventory;
    }

    /**
     * Returns the category at the specified index.
     *
     * @param index Index of the category.
     * @return Category at the given index.
     */
    public Category getCategory(int index) {
        return inventory.get(index);
    }

    /**
     * Returns the number of categories in the inventory.
     *
     * @return Number of categories.
     */
    public int getCategoryCount() {
        return inventory.size();
    }

    /**
     * Finds a category by name, ignoring case.
     *
     * @param categoryName Name of the category to find.
     * @return Matching category if found, or {@code null} otherwise.
     */
    public Category findCategoryByName(String categoryName) {
        for (Category category : inventory) {
            if (category.getName().equalsIgnoreCase(categoryName)) {
                return category;
            }
        }
        return null;
    }

    /**
     * Checks whether a category with the given name exists.
     *
     * @param categoryName Name of the category to check.
     * @return {@code true} if the category exists, {@code false} otherwise.
     */
    public boolean hasCategory(String categoryName) {
        return findCategoryByName(categoryName) != null;
    }

    /**
     * Prints the inventory to the console.
     */
    public void printInventory() {
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + "." + inventory.get(i));
        }
    }

    /**
     * Returns a string representation of the full inventory.
     *
     * @return Formatted inventory details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Category category : inventory) {
            sb.append(category).append("\n");
        }
        return sb.toString();
    }
}
