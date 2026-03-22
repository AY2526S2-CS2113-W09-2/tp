package seedu.duke.storage;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.model.Category;
import seedu.duke.model.Inventory;
import seedu.duke.model.Item;
import seedu.duke.model.items.Fruit;
import seedu.duke.model.items.Snack;
import seedu.duke.model.items.Toiletries;
import seedu.duke.model.items.Vegetable;
import seedu.duke.parser.AddItemCommandParser;
import seedu.duke.ui.UI;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final File dataFile;

    public Storage(String filePath) throws IOException {
        this.dataFile = new File(filePath);
        createFile();
    }

    private void createFile() throws IOException {
        if (dataFile.exists()) {
            return;
        }
        File parent = dataFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        dataFile.createNewFile();
    }

    public void load(Inventory inventory, UI ui) throws DukeException {
        try {
            List<String> lines = Files.readAllLines(dataFile.toPath());
            AddItemCommandParser addItemCommandParser = new AddItemCommandParser();

            for (String line : lines) {
                try {
                    Command command = parseStoredLine(line, addItemCommandParser);
                    command.execute(inventory, null);
                } catch (DukeException e) {
                    ui.showSkippedLine(line, e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new DukeException("Unable to read storage file.");
        }
    }

    private Command parseStoredLine(String line, AddItemCommandParser parser) throws DukeException {
        String category = extractCategory(line);

        switch (category) {
        case "fruits":
            return parser.handleFruit(line);
        case "snacks":
            return parser.handleSnack(line);
        case "toiletries":
            return parser.handleToiletries(line);
        case "vegetables":
            return parser.handleVegetables(line);
        default:
            throw new DukeException("Unknown category in storage.");
        }
    }

    private String extractCategory(String line) throws DukeException {
        String[] tokens = line.trim().split(" ");

        for (String token : tokens) {
            if (token.startsWith("category/")) {
                return token.substring("category/".length()).trim().toLowerCase();
            }
        }

        throw new DukeException("Missing category in storage line.");
    }

    public void save(Inventory inventory) throws DukeException {
        List<String> lines = new ArrayList<>();

        for (Category category : inventory.getCategories()) {
            for (Item item : category.getItems()) {
                lines.add(formatItem(item, category.getName()));
            }
        }

        try {
            Files.write(dataFile.toPath(), lines);
        } catch (IOException e) {
            throw new DukeException("Unable to save inventory.");
        }
    }

    private String formatItem(Item item, String categoryName) {
        String base = "item/" + item.getName()
                + " category/" + categoryName
                + " bin/" + item.getBinLocation()
                + " qty/" + item.getQuantity();

        if (item instanceof Fruit) {
            Fruit fruit = (Fruit) item;
            return base + " expiryDate/" + item.getExpiryDate()
                    + " size/" + fruit.getSize()
                    + " isRipe/" + fruit.isRipe();
        }

        if (item instanceof Snack) {
            Snack snack = (Snack) item;
            return base + " brand/" + snack.getBrand()
                    + " expiryDate/" + item.getExpiryDate();
        }

        if (item instanceof Toiletries) {
            Toiletries toiletries = (Toiletries) item;
            return base + " brand/" + toiletries.getBrand()
                    + " isLiquid/" + toiletries.isLiquid()
                    + " expiryDate/" + item.getExpiryDate();
        }

        if (item instanceof Vegetable) {
            Vegetable vegetable = (Vegetable) item;
            return base + " expiryDate/" + item.getExpiryDate()
                    + " isLeafy/" + vegetable.isLeafy();
        }

        return base;
    }
}
