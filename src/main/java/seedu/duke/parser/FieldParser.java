package seedu.duke.parser;

public class FieldParser {

    public static String extractField(String input,
                                      String startKey,
                                      String endKey) {
        int startIndex = input.indexOf(startKey);
        if (startIndex == -1) {
            return null;
        }

        startIndex += startKey.length();

        int endIndex;
        if (endKey != null) {
            endIndex = input.indexOf(endKey, startIndex);
            if (endIndex == -1) {
                endIndex = input.length();
            }
        } else {
            endIndex = input.length();
        }

        return input.substring(startIndex, endIndex).trim();
    }
}
