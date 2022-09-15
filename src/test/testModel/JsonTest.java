package testModel;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    //effects: checks if the item has the expected parameters.
    protected void checkItem(String name, int id, double price, int discountPercentage,
                             int dayCount, int quantity, int minStock, int maxStock, Item item) {
        assertEquals(name, item.getName());
        assertEquals(id, item.getId());
        assertEquals(price, item.getPrice());
        assertEquals(discountPercentage, item.getDiscountPercentage());
        assertEquals(quantity, item.getQuantity());
        assertEquals(dayCount, item.getDayCount());
        assertEquals(minStock, item.getMinStock());
        assertEquals(maxStock, item.getMaxStock());
    }
}

