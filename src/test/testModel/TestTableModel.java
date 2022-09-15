package testModel;

import model.Inventory;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.table.TableModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestTableModel {
    TableModel tableModel;
    Inventory testInventory;

    @BeforeEach
    void runBefore() {
        testInventory = new Inventory("MyInventory");
        Item i1 = new Item("BookTitle1", 1, 10, 20, 5, 50);
        testInventory.addItem(i1);

        Item i2 = new Item("BookTitle2", 2, 15, 5, 20, 100);
        testInventory.addItem(i2);
    }

    @Test
    void testTableModel() {
        tableModel = new model.TableModel(testInventory);
        assertEquals(2, tableModel.getRowCount());
    }

    @Test
    void testGetColumnCount() {
        tableModel = new model.TableModel(testInventory);
        assertEquals(8, tableModel.getColumnCount());
    }

    @Test
    void testGetRowCount() {
        tableModel = new model.TableModel(testInventory);
        assertEquals(2, tableModel.getRowCount());
    }

    @Test
    void testGetValueAt() {
        tableModel = new model.TableModel(testInventory);
        assertEquals("BookTitle1", tableModel.getValueAt(0,0));
        assertEquals(1, tableModel.getValueAt(0,1));
        assertEquals(10.0, tableModel.getValueAt(0,2));
        assertEquals(0, tableModel.getValueAt(0,3));
        assertEquals(20, tableModel.getValueAt(0,4));
        assertEquals(5, tableModel.getValueAt(0,5));
        assertEquals(50, tableModel.getValueAt(0,6));
        assertEquals(0, tableModel.getValueAt(0,7));
        assertNull(tableModel.getValueAt(0,8));
    }

    @Test
    void testColumnName() {
        tableModel = new model.TableModel(testInventory);
        assertEquals("Name", tableModel.getColumnName(0));
        assertEquals("Id", tableModel.getColumnName(1));
        assertEquals("Price", tableModel.getColumnName(2));
        assertEquals("Discount percentage", tableModel.getColumnName(3));
        assertEquals("Quantity", tableModel.getColumnName(4));
        assertEquals("Minimum quantity required in Stock", tableModel.getColumnName(5));
        assertEquals("Maximum quantity required in Stock", tableModel.getColumnName(6));
        assertEquals("Day count", tableModel.getColumnName(7));
    }

    @Test
    void testColumnClass() {
        tableModel = new model.TableModel(testInventory);
        assertEquals(String.class, tableModel.getColumnClass(0));
        assertEquals(int.class, tableModel.getColumnClass(1));
        assertEquals(Double.class, tableModel.getColumnClass(2));
        assertEquals(int.class, tableModel.getColumnClass(3));
        assertEquals(int.class, tableModel.getColumnClass(4));
        assertEquals(int.class, tableModel.getColumnClass(5));
        assertEquals(int.class, tableModel.getColumnClass(6));
        assertEquals(int.class, tableModel.getColumnClass(7));
    }

}
