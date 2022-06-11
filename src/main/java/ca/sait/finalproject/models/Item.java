package ca.sait.finalproject.models;

import java.io.Serializable;

/**
 *
 * @author Cole
 */
public class Item implements Serializable {
    private int itemId;
    private Category category;
    private String name;
    private double price;
    private String owner;
    
    public Item () {
        
    }
    public Item ( Category category, String name, double price, String owner) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.owner = owner;
    }
    
    public Item (int itemId, Category category, String name, double price, String owner) {
        this.itemId = itemId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.owner = owner;
    }
    
    
    public int getItemId() {
        return itemId;
    }

    public void setItemID(int itemID) {
        this.itemId = itemID;
    }
    
    public Category getCategory() {
        return category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
        
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getOwner() {
        return owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    
}
