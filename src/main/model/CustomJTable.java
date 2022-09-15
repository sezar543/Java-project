package model;

import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CustomJTable extends JFrame implements ActionListener {
    private TableModel tableModel;
    private JTable table;
    private Inventory itemsTable;
    private persistence.JsonReader jsonReader;
    private static final String JSON_STORE = "./data/inventory.json";
    private static final ImageIcon icon = new ImageIcon("./data/Inventory.jpg");
    private JScrollPane scrollPane;
    private final JFrame frame;
    private JFrame imageFrame;
    private Inventory myInventory;
    private JsonWriter jsonWriter;
    private JTextField text1 = new JTextField(20);
    private JTextField text2 = new JTextField(20);
    private JTextField text3 = new JTextField(20);
    private JTextField text4 = new JTextField(20);
    private JTextField text5 = new JTextField(20);
    private JTextField text6 = new JTextField(20);
    private JTextField text7 = new JTextField(20);
    private JTextField searchField = new JTextField(20);

    protected JButton loadButton;
    protected JButton saveButton;
    protected JButton addButton;
    protected JButton clearButton;
    protected JButton removeButton;
    protected JButton closeButton;
    protected JButton filterPriceButton;

    private JPanel topPanel;
    private JPanel removePanel;
    private JPanel addClearPanel;
    private JPanel southPanel;
    private JPanel textPanel;
    private JPanel topTopPanel;
    private JPanel topBtnPanel;

    private JLabel lblSearch;

    private TableRowSorter myTableRowSorter;

    public CustomJTable(String title) {
        super(title);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame = this;
        frame.setLayout(new BorderLayout());
        frame.setBounds(10, 10, 1200, 800);
        myInventory = new Inventory(title);
        tableModel = new TableModel(myInventory);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                printLog(EventLog.getInstance());
                //THEN you can exit the program
                System.exit(0);
            }
        });

        createTable();
        createButton();
        filterPrice();
        panelSetup();
        createTextPanel();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getContentPane().add(topBtnPanel);
        frameSetUp();
    }

    // MODIFIES: nothing
    // EFFECTS: it adds the button, label and price for the search feature of minimum price.
    public void filterPrice() {
        myTableRowSorter = new TableRowSorter(tableModel);
        table.setRowSorter(myTableRowSorter);
        lblSearch = new JLabel("MinPrice:");
        lblSearch.setBounds(247, 11, 46, 14);
        searchField.setBounds(293, 9, 131, 20);
        searchField.setColumns(10);
        filterPriceButton.setBounds(293, 9, 131, 20);
    }

    // MODIFIES: nothing
    // EFFECTS: instantiate the JButton variations
    public void createButton() {
        loadButton = new JButton("Load inventory");
        loadButton.addActionListener(this);
        loadButton.setBounds(75, 200, 90, 30);
        saveButton = new JButton("Save inventory");
        saveButton.addActionListener(this);
        saveButton.setBounds(75, 200, 90, 30);
        closeButton = new JButton("Close Application");
        closeButton.addActionListener(this);
        closeButton.setBounds(75, 200, 90, 30);
        addButton = new JButton("+ Add");
        clearButton = new JButton("Clear");
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
        removeButton = new JButton("Remove selected row");
        removeButton.addActionListener(this);
        filterPriceButton = new JButton("Search");
        filterPriceButton.addActionListener(this);
    }

    // MODIFIES: nothing
    // EFFECTS: it creates the text panel that contains the labels and panels to add an item
    public void createTextPanel() {
        textPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //Add JTextFields to the panel
        String[] dims = {"Name:", "Id:", "Price:", "Discount Percentage:", "Quantity:",
                "Minimum quantity required in Stock:", "Maximum quantity required in Stock:"};
        for (int i = 0; i < 7; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            textPanel.add(new JLabel(dims[i]), gbc);
            gbc.gridx = 5;
            gbc.gridy = i;
            helperCreateTextPanel(i, gbc);
        }
    }

    // MODIFIES: nothing
    // EFFECTS: the helper method for method "createTextPanel()".
    public void helperCreateTextPanel(int i, GridBagConstraints gbc) {
        switch (i) {
            case 0:
                textPanel.add(text1, gbc);
                break;
            case 1:
                textPanel.add(text2, gbc);
                break;
            case 2:
                textPanel.add(text3, gbc);
                break;
            case 3:
                textPanel.add(text4, gbc);
                break;
            case 4:
                textPanel.add(text5, gbc);
                break;
            case 5:
                textPanel.add(text6, gbc);
                break;
            default:
                textPanel.add(text7, gbc);
                break;
        }
    }

    // MODIFIES: nothing
    // EFFECTS: it puts and joins all the panels together.
    public void panelSetup() {
        topTopPanel = new JPanel();
        topBtnPanel = new JPanel();

        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        southPanel = new JPanel();
        textPanel = new JPanel(new GridBagLayout());
        removePanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        addClearPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topTopPanel.add(saveButton);
        topTopPanel.add(loadButton);
        topTopPanel.add(removeButton);
        topTopPanel.add(closeButton, BorderLayout.EAST);
        addClearPanel.add(addButton);
        addClearPanel.add(clearButton);
        southPanel.add(textPanel, BorderLayout.SOUTH);
        southPanel.add(addClearPanel, BorderLayout.SOUTH);
        southPanel.add(removePanel, BorderLayout.EAST);
        southPanel.add(addClearPanel, BorderLayout.CENTER);

        topTopPanel.add(lblSearch, BorderLayout.CENTER);
        topTopPanel.add(searchField, BorderLayout.NORTH);
        topTopPanel.add(filterPriceButton, BorderLayout.NORTH);

        topPanel.add(topTopPanel);
        topPanel.add(topBtnPanel);
    }

    // MODIFIES: nothing
    // EFFECTS: it creates the frame by adding three panels.
    public void frameSetUp() {
        frame.add(topTopPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(southPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: nothing
    // EFFECTS: it creates the table.
    public void createTable() {
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table);
        int[] dims = {150, 50, 150, 300, 150, 450, 450, 250};
        for (int i = 0; i < 8; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(dims[i]);
        }
    }

    // MODIFIES: nothing
    // EFFECTS: it adds the row associated with given item from the table
    public void addRow(Item item) {
        myInventory.addItem(item);
        tableModel.fireTableDataChanged();
        EventLog.getInstance().logEvent(new Event("The item with id "
                + item.getId()
                + " is added to the inventory "
                + myInventory.getName()
                + " ."));
    }

    // MODIFIES: nothing
    // EFFECTS: it removes the row associated with given item from the table
    public void removeRow(Item item) {
        int id = item.getId();
        myInventory.removeItem(id);
        tableModel.fireTableDataChanged();
        EventLog.getInstance().logEvent(new Event("The item with id "
                + item.getId()
                + " is removed from the inventory "
                + myInventory.getName()
                + " ."));
    }

    // MODIFIES: nothing
    // EFFECTS: it implements the save button to save the data into the JSON_STORE file.
    public void saveButton() {
        try {
            jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(myInventory);
            jsonWriter.close();
            //System.out.println("Saved " + myInventory.getName() + " to " + JSON_STORE);
            EventLog.getInstance().logEvent(new Event("The inventory is saved to the file" + JSON_STORE + " ."));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: nothing
    // EFFECTS: it does implement the save button to save the data into the JSON_STORE file.
    public Item createItem() {
        String name = text1.getText();
        int id = Integer.parseInt(text2.getText());
        double price = Double.parseDouble(text3.getText());
        int discountPercentage = Integer.parseInt(text4.getText());
        int quantity = Integer.parseInt(text5.getText());
        int minStock = Integer.parseInt(text6.getText());
        int maxStock = Integer.parseInt(text7.getText());
        Item newItem = new Item(name, id, price, quantity, minStock, maxStock);
        newItem.setDiscountPercentage(discountPercentage);
        return newItem;
    }

    // MODIFIES: nothing
    // EFFECTS: the inventory loads and a message dialog  and an image appears.
    protected void loadData() {
        itemsTable = new Inventory("title");
        jsonReader = new persistence.JsonReader(JSON_STORE);
        try {
            itemsTable = jsonReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Item i : itemsTable.getItems()) {
            //myInventory.addItem(i);
            addRow(i);
        }
        EventLog.getInstance().logEvent(new Event("The inventory is loaded from the file" + JSON_STORE + "."));
        JOptionPane.showMessageDialog(null, "The inventory is successfully loaded!");
        imageFrame = new JFrame();
        JLabel label = new JLabel(icon);
        imageFrame.add(label);
        imageFrame.pack();
        imageFrame.setVisible(true);
    }

    // MODIFIES: nothing
    // EFFECTS: it makes all the labels for the "add item" feature clear.
    public void clearTextLabels() {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
        text7.setText("");
    }

    // MODIFIES: nothing
    // EFFECTS: an event happens based on the button pressed by user.
    @Override
    public void actionPerformed(ActionEvent event) {
        JButton actionSource = (JButton) event.getSource();
        if (actionSource.equals(loadButton)) {
            loadData();
        } else if (actionSource.equals(addButton)) {
            Item newItem = createItem();
            addRow(newItem);
        } else if (actionSource.equals(clearButton)) {
            clearTextLabels();
        } else if (actionSource.equals(saveButton)) {
            saveButton();
        } else if (actionSource.equals(removeButton)) {
            if (table.getSelectedRow() != -1) {
                int selectedRow = table.getSelectedRow();
                //int idItem = (int) tableModel.getValueAt(selectedRow, 1);
                Item removedItem = myInventory.findItem((int) tableModel.getValueAt(selectedRow, 1));
                removeRow(removedItem);
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            }
        } else if (actionSource.equals(closeButton)) {
            printLog(EventLog.getInstance());
            frame.dispose();
        } else {
            //if (actionSource.equals(filterPriceButton))
            //String minPrice = searchField.getText(); added this as argument to the line below
            //String maxPrice = searchField.getText();
            myTableRowSorter.setRowFilter(new MyRowFilter(searchField.getText()));
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }

    public static void main(String[] args) {
        CustomJTable myApp = new CustomJTable("Custom JTable");
        myApp.setVisible(true);
    }
}
