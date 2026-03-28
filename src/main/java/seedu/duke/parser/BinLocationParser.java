package seedu.duke.parser;

import seedu.duke.exception.DukeException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BinLocationParser {
    private static final Logger logger = Logger.getLogger(BinLocationParser.class.getName());

    private static final String INVALID_BIN_SEARCH_MESSAGE =
            "Bin search must be either LETTER-NUMBER (e.g. A-10), "
                    + "LETTER only (e.g. A), or NUMBER only (e.g. 10).";

    public static String parseSearchInput(String input) throws DukeException {
        assert input != null : "BinLocationParser received null input.";

        String normalizedInput = input.trim().toLowerCase();

        if (normalizedInput.isEmpty()) {
            logger.log(Level.WARNING, "Bin search input is empty.");
            throw new DukeException(INVALID_BIN_SEARCH_MESSAGE);
        }

        int dashIndex = normalizedInput.indexOf('-');

        if (dashIndex == -1) {
            if (isSingleLetter(normalizedInput) || isInteger(normalizedInput)) {
                logger.log(Level.FINE, "Parsed bin search input: " + normalizedInput);
                return normalizedInput;
            }

            logger.log(Level.WARNING, "Invalid bin search input without dash: " + normalizedInput);
            throw new DukeException(INVALID_BIN_SEARCH_MESSAGE);
        }

        if (dashIndex != normalizedInput.lastIndexOf('-')) {
            logger.log(Level.WARNING, "Invalid bin search input with multiple dashes: " + normalizedInput);
            throw new DukeException(INVALID_BIN_SEARCH_MESSAGE);
        }

        String firstPart = normalizedInput.substring(0, dashIndex);
        String secondPart = normalizedInput.substring(dashIndex + 1);

        if (!isSingleLetter(firstPart) || !isInteger(secondPart)) {
            logger.log(Level.WARNING, "Invalid bin search input format: " + normalizedInput);
            throw new DukeException(INVALID_BIN_SEARCH_MESSAGE);
        }

        logger.log(Level.FINE, "Parsed bin search input: " + normalizedInput);
        return normalizedInput;
    }

    public static boolean isMatchingBin(String itemBinLocation, String binInput) {
        assert itemBinLocation != null : "BinLocationParser received null item bin location.";
        assert binInput != null : "BinLocationParser received null search input.";

        String normalizedBinLocation = itemBinLocation.trim().toLowerCase();
        String[] binParts = normalizedBinLocation.split("-", 2);

        assert binParts.length == 2 : "Stored bin location is not in LETTER-NUMBER format: " + itemBinLocation;

        String binLetter = binParts[0];
        String binNumber = binParts[1];

        if (binInput.contains("-")) {
            return normalizedBinLocation.equals(binInput);
        }

        if (Character.isLetter(binInput.charAt(0))) {
            return binLetter.equals(binInput);
        }

        return binNumber.equals(binInput);
    }

    private static boolean isSingleLetter(String input) {
        assert input != null : "isSingleLetter received null input.";
        return input.length() == 1 && Character.isLetter(input.charAt(0));
    }

    private static boolean isInteger(String input) {
        assert input != null : "isInteger received null input.";

        if (input.isEmpty()) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
