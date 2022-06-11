/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sait.finalproject.dataaccess;

import ca.sait.finalproject.models.Category;
import ca.sait.finalproject.models.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cole
 */


public class InventoryDB {
        public List<Item> getAll(String email) throws Exception {
        List<Item> items = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM item JOIN user ON (user.email = item.owner) JOIN category ON (category.category_id = item.category) WHERE email = ?";
       // String sql = "SELECT * FROM item JOIN user ON (user.email = item.owner) JOIN category ON (category.category_id = item.category)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            // rs = con.createStatement().executeQuery(sql);

            while (rs.next()) {
                int itemId = rs.getInt(1);
                int categoryId = rs.getInt(2);
                String name = rs.getString(3);
                double price = rs.getDouble(4);
                String owner = rs.getString(5);
            //    String email = rs.getString(6);
                String catName = rs.getString(13);
                
                Category category = new Category(categoryId, catName);
                
                //deleted itemId
                Item item = new Item(itemId, category, name, price, owner);
                items.add(item);

            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return items;
    }

    public Item get(String email) throws Exception {
        Item item = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
       // String sql = "SELECT * FROM item WHERE owner = ? LIMIT 1";
        String sql = "SELECT * FROM item INNER JOIN user ON (user.email = item.owner) JOIN category ON (category.category_id = item.category) WHERE owner = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(6, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                int itemId = rs.getInt(1);
                int categoryId = rs.getInt(2);
                String name = rs.getString(3);
                double price = rs.getDouble(4);
                String owner = rs.getString(5);
             //   String email = rs.getString(6);
                String catName = rs.getString(13);

                Category category = new Category(categoryId, catName);

                item = new Item(itemId, category, name, price, owner);
                
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return item;
    }

    public boolean insert(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO item(`category`, `item_name`, `price`, `owner`) VALUES ( ?, ?, ?, ?)";

        boolean inserted = false;

        try {
            ps = con.prepareStatement(sql);
         //   ps.setInt(1, item.getItemId());
            ps.setInt(1, item.getCategory().getId());
            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner());

            inserted = ps.executeUpdate() != 0 ? true : false;

        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return inserted;
    }

    public boolean update(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE item SET `category` = ?, `item_name` = ?, `price` = ?, `owner` = ? WHERE  `item_id` = ?";
        boolean updated;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getCategory().getId());
            ps.setString(2, item.getName());
            ps.setDouble(3, item.getPrice());
            ps.setString(4, item.getOwner());
            ps.setInt(5, item.getItemId());
            updated = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return updated;
    }

    public boolean delete(Item item) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM item WHERE item_id = ?";

        boolean deleted;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, item.getItemId());
            deleted = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return deleted;
    }

}
