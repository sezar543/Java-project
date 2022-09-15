package model;

import org.json.JSONObject;
import persistence.Writable;

// This class creates item to be included in teh inventory object.
public class Item implements Writable {

    private String name;
    private int id;
    private double price;
    private int discountPercentage;
    // dayCount is used to keep track of the number of items sold
    // within certain number of days and will be used to check if a discount is necessary
    private int dayCount;
    private int quantity;
    // minStock is the minimum number of items that manager requires to have in his inventory
    private int minStock;
    private int maxStock;

    public Item(String name, int id, double price, int quantity, int minStock, int maxStock) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.discountPercentage = 0;
        this.dayCount = 0;
        this.quantity = quantity;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public double getPrice() {
        return this.price;
    }

    public int getDiscountPercentage() {
        return this.discountPercentage;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getMinStock() {
        return this.minStock;
    }

    public int getMaxStock() {
        return this.maxStock;
    }

    public int getDayCount() {
        return this.dayCount;
    }

    //public int getDayCount() { return this.dayCount; }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public void setDiscountPercentage(int discount) {
        if (discount < 0) {
            System.out.println("It is not possible to apply a negative discount!");
        } else if (discount >= 100) {
            System.out.println("It is not possible to apply a discount of 100% or more!");
        } else {
            this.discountPercentage = discount;
        }
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    // MODIFIES:
    // EFFECTS: removes the quantity of sold items from the quantity
    public void sellQuantity(int quantity) {
        if (quantity > this.quantity) {
            System.out.println("There is not enough quantity in the stock! Sale cannot happen");
        } else {
            this.setQuantity(this.quantity - quantity);
        }
    }

    // MODIFIES:
    // EFFECTS: if the current quantity plus the wanted buying quantity is less than maxStock, it adds the quantity of
    // bought items from the quantity; otherwise it does not allow you to buy the wanted amount of the item.
    public void buyQuantity(int quantity) {
        if (this.quantity + quantity > this.maxStock) {
            System.out.print("We cannot purchase the given quantity because ");
            System.out.println("there is not enough space in the storage room! ");
        } else {
            this.setQuantity(this.quantity + quantity);
        }
    }

    // MODIFIES: nothing
    // EFFECTS: returns the json object with the parameters of this.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("id", id);
        json.put("price", price);
        json.put("dayCount", dayCount);
        json.put("discountPercentage", discountPercentage);
        json.put("quantity", quantity);
        json.put("minStock", minStock);
        json.put("maxStock", maxStock);
        return json;
    }
}
