package seedu.duke.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a category in the inventory.
 * Each category stores a name and a list of items that belong to it.
 */
public class Category {
    private String name;
    private final List<Item> items;

    /**
     * Creates a category with the given name.
     *
     * @param name Name of the category.
     */
    public Category(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    /**
     * Returns the name of this category.
     *
     * @return Name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the name of this category.
     *
     * @param name New category name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of items in this category.
     *
     * @return List of items in the category.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Returns the item at the specified index.
     *
     * @param index Index of the item.
     * @return Item at the given index.
     */
    public Item getItem(int index) {
        return items.get(index);
    }

    /**
     * Returns the number of items in this category.
     *
     * @return Number of items stored.
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Returns whether this category contains no items.
     *
     * @return {@code true} if the category is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Adds an item to this category.
     *
     * @param item Item to be added.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes the item at the specified index.
     *
     * @param index Index of the item to remove.
     * @throws IndexOutOfBoundsException If the index is invalid.
     */
    public void removeItem(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid item index");
        }
        items.remove(index);
    }

    /**
     * Finds an item in this category by name, ignoring case.
     *
     * @param itemName Name of the item to search for.
     * @return Matching item if found, or {@code null} otherwise.
     */
    public Item findItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of this category and its items.
     *
     * @return Formatted category details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Category: ").append(name).append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append("  ").append(i + 1).append(". ").append(items.get(i)).append("\n");
        }
        return sb.toString();
    }
}
