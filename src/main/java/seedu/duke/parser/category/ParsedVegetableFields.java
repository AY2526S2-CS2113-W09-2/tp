package seedu.duke.parser.category;

public class ParsedVegetableFields {
    public final String expiryDate;
    public final boolean isLeafy;

    public ParsedVegetableFields(String expiryDate, boolean isLeafy) {
        this.expiryDate = expiryDate;
        this.isLeafy = isLeafy;
    }
}
