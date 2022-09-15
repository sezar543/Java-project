package testModel;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    private Item i1;

    @BeforeEach
    void runBefore() {
        i1 = new Item("BookTitle1", 1, 10, 20, 5, 50);
    }

    @Test
    void testGetName() {
        //Item i1 = new Item("BookTitle1", 1, 10, 20, 5, 50);
        assertEquals("BookTitle1", i1.getName());
    }

    @Test
    void testGetId() {
        assertEquals(1, i1.getId());
    }

    @Test
    void testGetPrice() {
        assertEquals(10, i1.getPrice());
    }

    @Test
    void testGetQuantity() {
        assertEquals(20, i1.getQuantity());
    }

    @Test
    void testGetMinStock() {
        assertEquals(5, i1.getMinStock());
    }

    @Test
    void testGetMaxStock() {
        assertEquals(50, i1.getMaxStock());
    }

    @Test
    void testSetDiscountPercentage() {
        i1.setDiscountPercentage(10);
        assertEquals(10, i1.getDiscountPercentage());
        i1.setDiscountPercentage(-5);
        assertEquals(10, i1.getDiscountPercentage());
        i1.setDiscountPercentage(100);
        assertEquals(10, i1.getDiscountPercentage());
    }

    @Test
    void testSellQuantity() {
        i1.sellQuantity(7);
        assertEquals(13, i1.getQuantity());
        i1.sellQuantity(4);
        assertEquals(9, i1.getQuantity());
        i1.sellQuantity(10);
        assertEquals(9, i1.getQuantity());
    }

    @Test
    void testBuyQuantity() {
        i1.buyQuantity(7);
        assertEquals(27, i1.getQuantity());
        i1.buyQuantity(4);
        assertEquals(31, i1.getQuantity());
        i1.buyQuantity(20);
        assertEquals(31, i1.getQuantity());
    }

    @Test
    void testSetters() {
        i1 = new Item("BookTitle1", 1, 10, 20, 5, 50);
        i1.setId(13);
        i1.setName("BookTitle2");
        i1.setQuantity(15);
        i1.setPrice(100);
        i1.setMinStock(10);
        i1.setMaxStock(20);
        assertEquals(13, i1.getId());
        assertEquals("BookTitle2", i1.getName());
        assertEquals(15, i1.getQuantity());
        assertEquals(100, i1.getPrice());
        assertEquals(10, i1.getMinStock());
        assertEquals(20, i1.getMaxStock());
    }

}
