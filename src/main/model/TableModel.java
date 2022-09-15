package model;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * TableDemo is just like SimpleTableDemo, except that it
 * uses a custom TableModel.
 */
public class TableModel extends DefaultTableModel {
    private String[] columnNames = {"Name",
            "Id",
            "Price",
            "Discount percentage",
            "Quantity",
            "Minimum quantity required in Stock",
            "Maximum quantity required in Stock",
            "Day count"};
    private List<Item> itemsTable;

    public TableModel(Inventory inventory) {
        this.itemsTable = inventory.getItems();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        int size;
        if (itemsTable == null) {
            size = 0;
        } else {
            size = itemsTable.size();
        }
        return size;
    }


    // MODIFIES: nothing
    // EFFECTS: returns the object located at given row and column.
    public Object getValueAt(int row, int col) {
        Object temp = null;
        if (col == 0) {
            temp = itemsTable.get(row).getName();
        } else if (col == 1) {
            temp = itemsTable.get(row).getId();
        } else if (col == 2) {
            temp = itemsTable.get(row).getPrice();
        } else if (col == 3) {
            temp = itemsTable.get(row).getDiscountPercentage();
        } else if (col == 4) {
            temp = itemsTable.get(row).getQuantity();
        } else if (col == 5) {
            temp = itemsTable.get(row).getMinStock();
        } else if (col == 6) {
            temp = itemsTable.get(row).getMaxStock();
        } else if (col == 7) {
            temp = itemsTable.get(row).getDayCount();
        } else {
            return null;
        }
        return temp;
    }

    // MODIFIES: nothing
    // EFFECTS: returns the string of column name of given column in JTable.
    public String getColumnName(int col) {
        return columnNames[col];
    }

    // MODIFIES: nothing
    // EFFECTS: returns the class of the case at given column in JTable.
    public Class getColumnClass(int col) {
        if (col == 0) {
            return String.class;
        } else if (col == 2) {
            return Double.class;
        } else {
            return Integer.class;
        }
    }

}
