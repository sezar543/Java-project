package testModel;

import model.Inventory;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestInventory {
    private Inventory testInventory;
    private Item i1;
    private Item i2;
    private Item i3;

    @BeforeEach
    void runBefore() {
        testInventory = new Inventory("MyInventory");
        Item i1 = new Item("BookTitle1", 1, 10, 20, 5, 50);
        testInventory.addItem(i1);

        Item i2 = new Item("BookTitle2", 2, 15, 5, 20, 100);
        testInventory.addItem(i2);

        Item i3 = new Item("Toy1", 3, 45, 10, 10, 80);
        testInventory.addItem(i3);
    }

    @Test
    void testGetItems() {
        assertEquals(3, testInventory.getItems().size());
    }

    @Test
    void testFindItem() {
        Item item = testInventory.findItem(2);
        assertEquals(15, item.getPrice());
        assertNull(testInventory.findItem(5));
    }

    @Test
    void testAddItem() {
        Item i4 = new Item("Toy2", 4, 40, 11, 15, 60);
        Item repeatedItem = testInventory.findItem(1);
        testInventory.addItem(repeatedItem);
        assertEquals(3, testInventory.getItems().size());
        testInventory.addItem(i4);
        assertEquals(4, testInventory.getItems().size());
    }

    @Test
    void testRemoveItem() {
        //i3 = testInventory.findItem(3);
        Item item = testInventory.findItem(3);
        int id = item.getId();
        testInventory.removeItem(id);
        assertNull(testInventory.findItem(3));
    }

}
