package ui;

import model.Item;
import model.Inventory;

public class Main {

    public static void main(String[] args) {
        Inventory inventory = new Inventory("MyInventory");
        loadItems(inventory);
        InfoManager info = new InfoManager(inventory);
        System.out.println("You are now in the Inventory information console.");
        info.handleUserInput();
    }

    private static void loadItems(Inventory inventory) {
        Item i1 = new Item("BookTitle1", 1, 10, 20, 5, 50);
        //String name, int id, double price, int quantity, int minStock
        //no need initializeGym1(g1);
        inventory.addItem(i1);

        Item i2 = new Item("BookTitle2", 2, 15, 5, 20, 100);
        //String name, int id, double price, int quantity, int minStock
        //no need initializeGym1(g1);
        inventory.addItem(i2);

        Item i3 = new Item("Toy1", 3, 45, 10, 10, 80);
        //String name, int id, double price, int quantity, int minStock
        //no need initializeGym1(g1);
        inventory.addItem(i3);

    }


}
