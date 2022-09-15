package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// this class creates the Inventory object including a list of items and the name of inventory.
public class Inventory implements Writable {

    private List<Item> items;
    private String name;

    public Inventory(String name) {
        this.items = new ArrayList<>();
        this.name = name;
    }

    // getters
    public List<Item> getItems() {
        return this.items;
    }

    public String getName() {
        return this.name;
    }


    // REQUIRES: item != null
    // MODIFIES: this
    // EFFECTS: adds item to this inventory
    public void addItem(Item item) {
        boolean check = true;
        for (Item i : items) {
            if (Objects.equals(i.getId(), item.getId())) {
                System.out.print("An item with the same Id already exists in the inventory.");
                System.out.println(" You cannot add this item!");
                check = false;
                break;
            }
        }
        if (check) {
            items.add(item);
        }
    }

    // MODIFIES: this
    // EFFECTS: find item with the given id
    public Item findItem(int id) {
        for (Item i : items) {
            if (Objects.equals(i.getId(), id)) {
                return i;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: remove item with the given id
    public void removeItem(int id) {
        Item item = findItem(id);
        this.items.remove(item);
    }

    // MODIFIES:
    // EFFECTS: gives the size of items (this)
    public int getSize() {
        return this.items.size();
    }

    // EFFECTS: returns the Json object with the given name and items
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: returns items in this inventory as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Item item : items) {
            jsonArray.put(item.toJson());
        }
        return jsonArray;
    }
}
