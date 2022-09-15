package testModel;

import model.Item;
import model.Inventory;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Inventory inventory = new Inventory("Inventory saving");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Inventory inventory = new Inventory("Test Inventory");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyInventory.json");
            writer.open();
            writer.write(inventory);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyInventory.json");
            inventory = reader.read();
            assertEquals("Test Inventory", inventory.getName());
            assertEquals(0, inventory.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Inventory inventory = new Inventory("Inventory saving");
            Item item1 = new Item("toy", 1, 100, 20, 10, 50);
            item1.setDiscountPercentage(5);
            inventory.addItem(item1);
            inventory.addItem(new Item("car", 2, 10000, 7, 2, 10));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralInventory.json");
            writer.open();
            writer.write(inventory);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralInventory.json");
            inventory = reader.read();
            assertEquals("Inventory saving", inventory.getName());
            List<Item> items = inventory.getItems();
            assertEquals(2, items.size());
            checkItem("toy", 1, 100, 5, 0,20, 10, 50, items.get(0));
            checkItem("car", 2, 10000, 0, 0, 7, 2, 10, items.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}