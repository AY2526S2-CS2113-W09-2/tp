package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.model.items.Icecream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class IcecreamTest {
    @Test
    public void constructor_validInput_success() {
        Icecream icecream = new Icecream("Vanilla Cup", 3, "B1",
                "2026-03-28", "Vanilla", false);

        assertEquals("Vanilla Cup", icecream.getName());
        assertEquals(3, icecream.getQuantity());
        assertEquals("B1", icecream.getBinLocation());
        assertEquals("2026-03-28", icecream.getExpiryDate());
        assertEquals("Vanilla", icecream.getFlavour());
        assertFalse(icecream.isDairyFree());
    }
}