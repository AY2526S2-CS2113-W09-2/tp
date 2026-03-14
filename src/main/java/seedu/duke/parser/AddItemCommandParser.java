package seedu.duke.parser;

import seedu.duke.command.AddItemCommand;
import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.parser.category.FruitParser;
import seedu.duke.parser.category.ParsedFruitFields;
import seedu.duke.parser.category.ParsedSnackFields;
import seedu.duke.parser.category.ParsedToiletriesFields;
import seedu.duke.parser.category.ParsedVegetableFields;
import seedu.duke.parser.category.SnackParser;
import seedu.duke.parser.category.ToiletriesParser;
import seedu.duke.parser.category.VegetableParser;

public class AddItemCommandParser {
    protected String itemName = null;
    protected String categoryName = null;
    protected String bin = null;
    protected int quantity = 0;

    protected String brand = "N/A";
    protected String expiryDate = "N/A";
    protected String size = "N/A";
    protected boolean isRipe = false;
    protected boolean isLeafy = false;
    protected boolean isLiquid = false;

    private void resetFields() {
        itemName = null;
        categoryName = null;
        bin = null;
        quantity = 0;
        brand = "N/A";
        expiryDate = "N/A";
        size = "N/A";
        isRipe = false;
        isLeafy = false;
        isLiquid = false;
    }

    private void validateRequiredFields(String input, String... fields) throws DukeException {
        for (String field: fields) {
            if (!input.contains(field)) {
                throw new DukeException("Missing required field: " + field);
            }
        }
    }

    private void validateOrder(String input, String... fields) throws DukeException {
        int previous = -1;

        for (String field: fields) {
            int current = input.indexOf(field);

            if (current != -1) {
                if (current < previous) {
                    throw new DukeException("Fields must follow the correct order.");
                }
                previous = current;
            }
        }
    }

    private void parseCommonFields(String input) throws DukeException {
        itemName = FieldParser.extractField(input, "item/", "category/");
        categoryName = FieldParser.extractField(input, "category/", "bin/");

        bin = FieldParser.extractField(input, "bin/", "qty/");
        if (bin == null || bin.trim().isEmpty()) {
            throw new DukeException("Missing bin location.");
        }

        String quantityString = FieldParser.extractField(input, "qty/", null);
        if (quantityString == null || quantityString.trim().isEmpty()) {
            throw new DukeException("Missing quantity.");
        }

        quantityString = quantityString.trim().split(" ", 2)[0];

        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            throw new DukeException("Quantity must be an integer.");
        }

        if (quantity <= 0) {
            throw new DukeException("Quantity must be a positive integer.");
        }
    }

    private Command buildCommand() {
        return new AddItemCommand(itemName, categoryName, bin, quantity,
                brand, expiryDate, size, isRipe, isLeafy, isLiquid);
    }

    public Command handleFruit(String input) throws DukeException {
        resetFields();
        validateRequiredFields(input, "item/", "category/", "bin/", "qty/",
                "expiryDate/", "size/", "isRipe/");
        validateOrder(input, "item/", "category/", "bin/", "qty/",
                "expiryDate/", "size/", "isRipe/");
        parseCommonFields(input);

        ParsedFruitFields fruitFields = FruitParser.parse(input);
        expiryDate = fruitFields.expiryDate;
        size = fruitFields.size;
        isRipe = fruitFields.isRipe;

        return buildCommand();
    }

    public Command handleSnack(String input) throws DukeException {
        resetFields();
        validateRequiredFields(input, "item/", "category/", "bin/", "qty/",
                "brand/", "expiryDate/");
        validateOrder(input, "item/", "category/", "bin/", "qty/",
                "brand/", "expiryDate/");
        parseCommonFields(input);

        ParsedSnackFields snackFields = SnackParser.parse(input);
        brand = snackFields.brand;
        expiryDate = snackFields.expiryDate;

        return buildCommand();
    }

    public Command handleToiletries(String input) throws DukeException {
        resetFields();
        validateRequiredFields(input, "item/", "category/", "bin/", "qty/",
                "brand/", "isLiquid/");
        validateOrder(input, "item/", "category/", "bin/", "qty/",
                "brand/", "isLiquid/");
        parseCommonFields(input);

        ParsedToiletriesFields toiletriesFields = ToiletriesParser.parse(input);
        brand = toiletriesFields.brand;
        isLiquid = toiletriesFields.isLiquid;

        return buildCommand();
    }

    public Command handleVegetables(String input) throws DukeException {
        resetFields();
        validateRequiredFields(input, "item/", "category/", "bin/", "qty/",
                "expiryDate/", "isLeafy/");
        validateOrder(input, "item/", "category/", "bin/", "qty/",
                "expiryDate/", "isLeafy/");
        parseCommonFields(input);

        ParsedVegetableFields vegetableFields = VegetableParser.parse(input);
        expiryDate = vegetableFields.expiryDate;;
        isLeafy = vegetableFields.isLeafy;

        return buildCommand();
    }
}
