package seedu.duke.parser.category;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.DateParser;
import seedu.duke.parser.FieldParser;

/**
 * Parses fields that are common across multiple item categories.
 */
public class CommonFieldParser {
    public final String itemName;
    public final String categoryName;
    public final String bin;
    public final int quantity;
    public final String expiryDate;

    /**
     * Creates a {@code CommonFieldParser} object with the parsed common fields.
     *
     * @param itemName Name of the item.
     * @param categoryName Name of the category.
     * @param bin Bin location of the item.
     * @param quantity Quantity of the item.
     * @param expiryDate Expiry date of the item.
     */
    private CommonFieldParser(String itemName, String categoryName,
                              String bin, int quantity, String expiryDate) {
        this.itemName = itemName;
        this.categoryName = categoryName;
        this.bin = bin;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    /**
     * Parses the common fields from the given input string.
     *
     * @param input User input containing common item fields.
     * @param fieldAfterExpiry Field marker that appears after {@code expiryDate/}.
     * @return A {@code CommonFieldParser} containing the parsed values.
     * @throws DukeException If any required field is missing or invalid.
     */
    public static CommonFieldParser parse(String input, String fieldAfterExpiry) throws DukeException {
        assert input != null : "CommonFieldParser received null input.";

        String categoryName = FieldParser.extractField(
                input, "category/", "item/");
        String itemName = FieldParser.extractField(
                input, "item/", "bin/");

        String bin = FieldParser.extractField(input, "bin/", "qty/");
        if (bin == null || bin.trim().isEmpty()) {
            throw new DukeException("Missing bin location.");
        }

        String quantityString = FieldParser.extractField(
                input, "qty/", "expiryDate/");
        int quantity = parseQuantity(quantityString);

        String expiryDate = FieldParser.extractField(
                input, "expiryDate/", fieldAfterExpiry);
        validateExpiryDate(expiryDate);

        return new CommonFieldParser(itemName, categoryName, bin, quantity, expiryDate);
    }

    /**
     * Parses and validates the quantity field.
     *
     * @param quantityString Quantity string extracted from user input.
     * @return The parsed quantity as an integer.
     * @throws DukeException If the quantity is missing, non-numeric, or not positive.
     */
    public static int parseQuantity(String quantityString) throws DukeException {
        if (quantityString == null
                || quantityString.trim().isEmpty()) {
            throw new DukeException("Missing quantity.");
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityString.trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Quantity must be an integer.");
        }

        if (quantity <= 0) {
            throw new DukeException("Quantity must be a positive integer.");
        }

        return quantity;
    }

    /**
     * Validates the expiry date string.
     *
     * @param expiryDate Expiry date to validate.
     * @throws DukeException If the expiry date is missing or invalid.
     */
    public static void validateExpiryDate(String expiryDate) throws DukeException {
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new DukeException("Missing expiry date.");
        }
        DateParser.validateDate(expiryDate);
    }
}
