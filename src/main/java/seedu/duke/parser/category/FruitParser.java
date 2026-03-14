package seedu.duke.parser.category;

import seedu.duke.exception.DukeException;
import seedu.duke.parser.DateParser;
import seedu.duke.parser.FieldParser;

public class FruitParser {
    public static ParsedFruitFields parse(String input) throws DukeException {
        String expiryDate = FieldParser.extractField(input, "expiryDate/", "size/");
        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new DukeException("Missing expiry date for fruit.");
        }
        DateParser.validateDate(expiryDate);

        String size = FieldParser.extractField(input, "size/", "isRipe/");
        if (size == null || size.trim().isEmpty()) {
            throw new DukeException("Missing size for fruit.");
        }

        String ripeString = FieldParser.extractField(input, "isRipe/", null);
        if (ripeString == null || ripeString.trim().isEmpty()) {
            throw new DukeException("Missing ripeness for fruit.");
        }

        if (!(ripeString.equalsIgnoreCase("true") || ripeString.equalsIgnoreCase("false"))) {
            throw new DukeException("Ripeness must be true or false");
        }
        boolean isRipe = Boolean.parseBoolean(ripeString);

        return new ParsedFruitFields(expiryDate, size, isRipe);
    }
}
