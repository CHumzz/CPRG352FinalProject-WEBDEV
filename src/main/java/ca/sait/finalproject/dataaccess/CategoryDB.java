package ca.sait.finalproject.dataaccess;

import ca.sait.finalproject.models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDB {
    
        public List<Category> getAll() throws Exception {
        List<Category> categories = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM category ";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // rs = con.createStatement().executeQuery(sql);

            while (rs.next()) {
               int id = rs.getInt(1);
               String name = rs.getString(2);

                Category category = new Category(id, name);

                
                categories.add(category);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return categories;
    }
        
        
        public Category get(int id) throws Exception {
        Category category = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM category WHERE category_id = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);

                category = new Category(id, name);
                
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return category;
    }
        
        public int fetch(String name) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        int id = 0;
        String sql = "SELECT category_id FROM category WHERE category_name = ? LIMIT 1";
        
           try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(2);     
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
            
            return id;
        }
        
        public boolean insert(Category category) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO category(`category_name`) VALUES ( ?)";

        boolean inserted = false;

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, category.getId());
            ps.setString(1, category.getName());

            inserted = ps.executeUpdate() != 0 ? true : false;

        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return inserted;
    }
        
        public boolean update(Category category) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE category SET `category_name` = ? WHERE  `category_id` = ?";
        boolean updated;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());

            updated = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return updated;
    }


}
