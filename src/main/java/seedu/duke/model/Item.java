package seedu.duke.model;

/**
 * Represents a generic item stored in the inventory.
 * An item has a name, quantity, bin location, and expiry date.
 */
public class Item {
    private String name;
    private int quantity;
    private String binLocation;
    private String expiryDate;

    /**
     * Creates an item with the given details.
     *
     * @param name Name of the item.
     * @param quantity Quantity of the item.
     * @param binLocation Storage bin location of the item.
     * @param expiryDate Expiry date of the item.
     */
    public Item(String name, int quantity, String binLocation,
                String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.binLocation = binLocation;
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the item name.
     *
     * @return Name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the item name.
     *
     * @param name New item name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the item quantity.
     *
     * @return Quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Updates the item quantity.
     *
     * @param quantity New quantity value.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the bin location of the item.
     *
     * @return Bin location.
     */
    public String getBinLocation() {
        return binLocation;
    }

    /**
     * Updates the bin location of the item.
     *
     * @param binLocation New bin location.
     */
    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    /**
     * Updates the expiry date of the item.
     *
     * @param expiryDate New expiry date.
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the expiry date of the item.
     *
     * @return Expiry date.
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Converts this item into a storage-friendly string format.
     *
     * @param categoryName Name of the category this item belongs to.
     * @return Storage string representation of the item.
     */
    public String toStorageString(String categoryName) {
        return "category/" + categoryName
                + " item/" + name
                + " bin/" + binLocation
                + " qty/" + quantity
                + " expiryDate/" + expiryDate;
    }

    /**
     * Returns a string representation of this item.
     *
     * @return Formatted item details.
     */
    @Override
    public String toString() {
        return String.format(
                "Name: %s, Quantity: %d, Bin: %s, Expiry: %s",
                name, quantity, binLocation, expiryDate
        );
    }
}
