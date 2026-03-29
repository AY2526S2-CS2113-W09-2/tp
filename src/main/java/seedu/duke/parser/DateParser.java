package seedu.duke.parser;

import seedu.duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses and validates date strings for item expiry dates.
 */
public class DateParser {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-M-d");

    /**
     * Validates that the given date string is present and follows the expected format.
     *
     * @param date Date string to validate.
     * @throws DukeException If the date is missing or invalid.
     */
    public static void validateDate(String date) throws DukeException {
        if (date == null || date.trim().isEmpty()) {
            throw new DukeException("Missing expiry date");
        }

        try {
            parseDate(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Please use yyyy-M-d.");
        }
    }

    /**
     * Parses the given date string into a {@code LocalDate}.
     *
     * @param date Date string to parse.
     * @return Parsed date as a {@code LocalDate}.
     * @throws DukeException If the date is missing or invalid.
     */
    public static LocalDate parseDate(String date) throws DukeException {
        if (date == null || date.trim().isEmpty()) {
            throw new DukeException("Missing expiry date");
        }

        try {
            return LocalDate.parse(date.trim(), FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date. Please use yyyy-M-d.");
        }
    }
}
