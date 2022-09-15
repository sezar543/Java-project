package testModel;

import model.Item;
import model.Inventory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/noSuchFile.json");
        try {
            Inventory inventory = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/testReaderEmptyInventory.json");
        try {
            Inventory inventory = reader.read();
            assertEquals("Empty Inventory", inventory.getName());
            assertEquals(0, inventory.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralInventory() {
        persistence.JsonReader reader = new persistence.JsonReader("./data/testReaderGeneralInventory.json");
        try {
            Inventory inventory = reader.read();
            assertEquals("My saved Inventory", inventory.getName());
            List<Item> items = inventory.getItems();
            assertEquals(2, items.size());
            checkItem("toy", 1, 100, 5, 0, 20,
                    10, 50, items.get(0));
            checkItem("car", 2, 10000, 0, 0, 7,
                    2, 10, items.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
