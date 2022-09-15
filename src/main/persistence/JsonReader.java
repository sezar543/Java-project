package persistence;

import model.Inventory;
import model.Item;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Inventory read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseInventory(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Inventory parseInventory(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Inventory inventory = new Inventory(name);
        addItems(inventory, jsonObject);
        return inventory;
    }

    // MODIFIES: inventory
    // EFFECTS: parses items from JSON object and adds them to inventory
    private void addItems(Inventory inventory, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("items");
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            addItem(inventory, nextItem);
        }
    }

    // MODIFIES: inventory
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addItem(Inventory inventory, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int id = jsonObject.getInt("id");
        double price = jsonObject.getDouble("price");
        int quantity = jsonObject.getInt("quantity");
        int discount = jsonObject.getInt("discountPercentage");
        int minStock = jsonObject.getInt("minStock");
        int maxStock = jsonObject.getInt("maxStock");
        int dayCount = jsonObject.getInt("dayCount");
        Item newItem = new Item(name, id, price, quantity, minStock, maxStock);
        newItem.setDayCount(dayCount);
        newItem.setDiscountPercentage(discount);
        inventory.addItem(newItem);
    }
}

