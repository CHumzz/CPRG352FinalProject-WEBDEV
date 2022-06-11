/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.finalproject.services;


import ca.sait.finalproject.dataaccess.InventoryDB;
import ca.sait.finalproject.models.*;
import java.util.List;
/**
 *
 * @author Cole
 */
public class InventoryService {
    private InventoryDB itemDB = new InventoryDB();
    
    public Item get(String owner) throws Exception{
        Item item = this.itemDB.get(owner);
        return item;
    }
    
    
    
    public List<Item> getAll(String email) throws Exception {
        List<Item> items = this.itemDB.getAll(email);
        return items;
    }
    
    
    
    public boolean insert (int itemId, Category category, String name, double price, String owner) throws Exception{
        Item item = new Item(itemId, category,  name,  price,  owner);
        return this.itemDB.insert(item);
    }
    
    public boolean update ( int itemId,Category category, String name, double price, String owner) throws Exception{
        Item item = new Item(itemId, category,  name,  price,  owner);
        return this.itemDB.update(item);
    }
    
    public boolean delete(int itemId) throws Exception {
        
        Item item = new Item();
        item.setItemID(itemId);
        return this.itemDB.delete(item);
    }
    
    
    
    
}
