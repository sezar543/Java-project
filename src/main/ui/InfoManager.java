package ui;

import model.Inventory;
import model.Item;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class InfoManager {

    private static final String ITEMS_COMMAND = "items";
    private static final String ID_COMMAND = "id";
    private static final String INVENTORY_CHANGE_COMMAND = "inventory";
    private static final String ADD_INVENTORY_COMMAND = "add";
    private static final String DISCOUNT_COMMAND = "discount";
    private static final String SOLD_COMMAND = "sold";
    private static final String PURCHASE_COMMAND = "purchase";
    private static final String GO_BACK_COMMAND = "back";
    private static final String SAVE_COMMAND = "save";
    private static final String RELOAD_COMMAND = "load";
    private static final String QUIT_COMMAND = "quit";
    private static final String JSON_STORE = "./data/inventory.json";
    private Scanner input;
    private JsonWriter jsonWriter;
    private persistence.JsonReader jsonReader;

    private boolean runProgram;
    private Inventory inventory;

    // EFFECTS: constructs inventory and runs application
    public InfoManager(Inventory inventory) {
        input = new Scanner(System.in);
        runProgram = true;
        this.inventory = new Inventory("My Inventory");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new persistence.JsonReader(JSON_STORE);
    }

    //EFFECTS: parses user input until user quits
    public void handleUserInput() {
        printInstructions();
        String str;
        while (runProgram) {
            str = getUserInputString();
            parseInput(str);
        }
    }

    //EFFECTS: prints menu options and info depending on input str
    private void parseInput(String str) {
        if (str.length() > 0) {
            switch (str) {
                case ITEMS_COMMAND: printItemInfo();
                    printInstructions();
                    break;
                case INVENTORY_CHANGE_COMMAND: handleIDInfoInput();
                    break;
                case ADD_INVENTORY_COMMAND: handleNewItemInput();
                    break;
                case SAVE_COMMAND: saveInventory();
                    break;
                case RELOAD_COMMAND: loadInventory();
                    break;
                case QUIT_COMMAND: runProgram = false;
                    endProgram();
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that command. Please try again.");
                    printInstructions();
                    break;
            }
        }
    }

    //EFFECTS: prints the info of all items in the inventory.
    private void printItemInfo() {
        System.out.println("All items in the inventory:");
        for (Item i : inventory.getItems()) {
            System.out.print(i.getName() + ", Id: " + i.getId() + ", Price: " + i.getPrice());
            System.out.print(", Discount: " + i.getDiscountPercentage() + "%, quantity: ");
            System.out.print(i.getQuantity() + ", Minimum quantity required in stock: " + i.getMinStock());
            System.out.println(", Maximum capacity in stock: " + i.getMaxStock());
        }
    }

    //EFFECTS: prints instructions to use in the main menu
    private void printInstructions() {
        System.out.println("\nYou can request the following:\n");
        System.out.println("Enter '" + ITEMS_COMMAND + "' for the list of items in the inventory.");
        System.out.println("Enter '" + INVENTORY_CHANGE_COMMAND + "' for applying a change in the inventory.");
        System.out.println("Enter '" + ADD_INVENTORY_COMMAND + "' for adding an item to in the inventory.");
        System.out.println("Enter '" + SAVE_COMMAND + "' for saving the inventory to a file.");
        System.out.println("Enter '" + RELOAD_COMMAND + "' for reloading inventory from a file.");
        System.out.println("To quit at any time, enter '" + QUIT_COMMAND + "'.");
    }

    //EFFECTS: prints instructions to apply changes on the items in the inventory
    private void itemChangeOptions() {
        System.out.println("Enter '" + DISCOUNT_COMMAND + "' for applying discount on the item.");
        System.out.println("Enter '" + PURCHASE_COMMAND + "' to order the item for adding to your stock.");
        System.out.println("Enter '" + SOLD_COMMAND + "' for reducing quantity of the  item that is sold.");
        System.out.println("Enter '" + GO_BACK_COMMAND + "' to go back to the main menu.");
    }

    //EFFECTS: save the id that user types and find the item with that id
    private void handleNewItemInput() {
        System.out.println("Enter the name of the item you want to add to the inventory.");
        String name = getUserInputString();
        System.out.println("Enter the ID of the item you want to add to the inventory.");
        String str = getUserInputString();
        int id = Integer.parseInt(str);
        System.out.println("Enter the price of the item you want to add to the inventory.");
        double price = Integer.parseInt(getUserInputString());
        System.out.println("Enter the quantity of the item.");
        int quantity = Integer.parseInt(getUserInputString());
        System.out.println("Enter the discount of the item (i.e. a number between 0 and 100)");
        int discountPercentage = Integer.parseInt(getUserInputString());
        System.out.println("Enter the minimum quantity of the item that you want to always have in stock");
        int minStock = Integer.parseInt(getUserInputString());
        System.out.println("Enter the max quantity of the item that you want to or you can have in stock");
        int maxStock = Integer.parseInt(getUserInputString());
        inventory.addItem(new Item(name, id, price, quantity, minStock, maxStock));
        System.out.println("The updated inventory:");
        printItemInfo();
        printInstructions();
    }

    //EFFECTS: save the id that user types and find the item with that id
    private void handleIDInfoInput() {
        if (inventory.getSize() == 0) {
            System.out.println("There is no item in the inventory!");
            handleUserInput();
        } else {
            System.out.println("Enter '" + ID_COMMAND + "' of an item in the inventory.");
            String str = getUserInputString();
            int id = Integer.parseInt(str);
            if (inventory.findItem(id) != null) {
                itemChangeOptions();
                handleExtraInfoInput(inventory.findItem(id));
            } else {
                System.out.println("There is no item with the Id " + id + " in the inventory. Try again!");
                handleIDInfoInput();
            }
        }
    }

    //EFFECTS: prints options of the type (discount or purchase or sell) of change user can do on the item that
    // he or she has chosen and request the required info and apply them
    private void handleExtraInfoInput(Item item) {
        String str = getUserInputString();
        if (str.length() > 0) {
            switch (str) {
                case GO_BACK_COMMAND:
                    printInstructions();
                    break;
                case DISCOUNT_COMMAND:
                case PURCHASE_COMMAND:
                case SOLD_COMMAND:
                    helperHandleExtraInfoInput(item, str);
                    break;
                default:
                    parseInput(str);
            }
        }
    }

    //EFFECTS: This is a helper method for method handleExtraInfoInput
    private void helperHandleExtraInfoInput(Item item, String str) {
        if (str.length() > 0) {
            switch (str) {
                case DISCOUNT_COMMAND:
                    System.out.println("Enter the percentage that you would like to increase discount of");
                    String str2 = getUserInputString();//changed int  discount=Double.parseInt(str2);
                    item.setDiscountPercentage((int) Double.parseDouble(str2) + item.getDiscountPercentage());
                    handleUserInput();
                    break;
                case PURCHASE_COMMAND:
                    System.out.println("Enter the quantity of the item " + item.getName());
                    item.buyQuantity(Integer.parseInt(getUserInputString()));
                    handleUserInput();
                    break;
                case SOLD_COMMAND:
                    System.out.println("Enter the quantity that is sold of the item " + item.getName());
                    item.sellQuantity(Integer.parseInt(getUserInputString()));
                    handleUserInput();
                    break;
                default:
                    parseInput(str);
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveInventory() {
        try {
            jsonWriter.open();
            jsonWriter.write(inventory);
            jsonWriter.close();
            System.out.println("Saved " + inventory.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        printInstructions();
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadInventory() {
        try {
            inventory = jsonReader.read();
            System.out.println("Loaded " + inventory.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        printInstructions();
    }

    //EFFECTS: removes white space and quotation marks around s
    private String makePrettyString(String s) {
        s = s.toLowerCase();
        s = s.trim();
        s = s.replaceAll("\"|\'", "");
        return s;
    }

    // Effects: it gets the input of user as string.
    private String getUserInputString() {
        String str = "";
        if (input.hasNext()) {
            str = input.nextLine();
            str = makePrettyString(str);
        }
        return str;
    }

    // Effects: it ends the program.
    public void endProgram() {
        System.out.println("Quitting...");
        input.close();
    }

}
