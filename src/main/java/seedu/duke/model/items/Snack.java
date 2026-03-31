package seedu.duke.model.items;

import seedu.duke.model.Item;

/**
 * Represents a snack item in the inventory.
 */
public class Snack extends Item {
    private String brand;
    private boolean isCrunchy;

    /**
     * Creates a snack item with the given details.
     *
     * @param name Name of the snack.
     * @param quantity Quantity of the snack.
     * @param binLocation Storage bin location.
     * @param brand Brand of the snack.
     * @param expiryDate Expiry date.
     */
    public Snack(String name, int quantity, String binLocation, String brand, String expiryDate) {
        super(name, quantity, binLocation, expiryDate);
        this.brand = brand;
        this.isCrunchy = isCrunchy;
    }

    /** @return Brand of the snack. */
    public String getBrand() {
        return brand;
    }

    /** @param brand New snack brand. */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Converts this snack into a storage-friendly string format.
     *
     * @param categoryName Name of the category this item belongs to.
     * @return Storage string representation.
     */
    @Override
    public String toStorageString(String categoryName) {
        return super.toStorageString(categoryName)
                + " brand/" + brand
                + " isCrunchy/" + isCrunchy;
    }

    /**
     * Returns a string representation of this snack.
     *
     * @return Formatted snack details.
     */
    @Override
    public String toString() {
        return "[Snack] " + super.toString()
                + ", Brand: " + brand
                + ", Crunchy: " + isCrunchy;
    }
}
