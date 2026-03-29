package seedu.duke.model.items;

import seedu.duke.model.Item;

/**
 * Represents a set meal item in the inventory.
 */
public class SetMeal extends Item {
    private String mealType;
    private String foodSize;

    /**
     * Creates a set meal item with the given details.
     *
     * @param name Name of the set meal.
     * @param quantity Quantity of the set meal.
     * @param binLocation Storage bin location.
     * @param expiryDate Expiry date.
     * @param mealType Type of meal.
     * @param foodSize Size of the meal.
     */
    public SetMeal(String name, int quantity, String binLocation,
                   String expiryDate, String mealType, String foodSize) {
        super(name, quantity, binLocation, expiryDate);
        this.mealType = mealType;
        this.foodSize = foodSize;
    }

    /** @return Size of the food. */
    public String getFoodSize() {
        return foodSize;
    }

    /** @param foodSize New food size. */
    public void setFoodSize(String foodSize) {
        this.foodSize = foodSize;
    }

    /** @return Type of meal. */
    public String getMealType() {
        return mealType;
    }

    /** @param mealType New meal type. */
    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    /**
     * Converts this set meal into a storage-friendly string format.
     *
     * @param categoryName Name of the category this item belongs to.
     * @return Storage string representation.
     */
    @Override
    public String toStorageString(String categoryName) {
        return super.toStorageString(categoryName)
                + " mealType/" + mealType
                + " foodSize/" + foodSize;
    }

    /**
     * Returns a string representation of this set meal.
     *
     * @return Formatted set meal details.
     */
    @Override
    public String toString() {
        return "[SetMeal] " + super.toString()
                + ", Meal Type: " + mealType
                + ", Food Size: " + foodSize;
    }
}
