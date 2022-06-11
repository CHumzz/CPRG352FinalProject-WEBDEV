package ca.sait.finalproject.dataaccess;

import ca.sait.finalproject.models.Role;
import ca.sait.finalproject.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDB {

    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM USER INNER JOIN role ON role_id = user.role";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            // rs = con.createStatement().executeQuery(sql);

            while (rs.next()) {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int roleId = rs.getInt(6);
                String roleName = rs.getString(8);

                Role role = new Role(roleId, roleName);

                User user = new User(email, active, firstName, lastName, password, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM USER INNER JOIN role ON role_id = user.role WHERE email = ? LIMIT 1";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int roleId = rs.getInt(6);
                String roleName = rs.getString(8);

                Role role = new Role(roleId, roleName);

                user = new User(email, active, firstName, lastName, password, role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return user;
    }

    public boolean insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO user(`email`, `first_name`, `last_name`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
        //String sql = "INSERT INTO 'inventorydb'.'user'(`email`, `first_name`, `last_name`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
        boolean inserted = false;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getId());

            inserted = ps.executeUpdate() != 0 ? true : false;

        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return inserted;
    }

    public boolean update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET `first_name` = ?, `active` = ?, `last_name` = ?, `password` = ?, `role` = ? WHERE  `email` = ?";
        boolean updated;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getFirstName());
            ps.setBoolean(2, user.isActive());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getId());
            ps.setString(6, user.getEmail());
            updated = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return updated;
    }

    public boolean disable(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "DELETE FROM user WHERE email = ?";
        String sql = "Update user SET active = 0 WHERE email = ?";

        boolean deleted;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            deleted = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return deleted;
    }
    
    public boolean delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        //String sql = "DELETE FROM user WHERE email = ?";
        String sql1 = "SET FOREIGN_KEY_CHECKS=0;";
        String sql = "DELETE user, item FROM user JOIN item ON (item.owner = user.email) WHERE email = ?;";
        String sql2 = "SET FOREIGN_KEY_CHECKS=1;";


        boolean deleted;

        try {
            ps = con.prepareStatement(sql1);
            ps.executeUpdate();
            ps = con.prepareStatement(sql);
            //ps = con.prepareStatement(sql2);
            ps.setString(1, user.getEmail());
            deleted = ps.executeUpdate() != 0;
            ps = con.prepareStatement(sql2);
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return deleted;
    }
     public boolean activate(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "Update user SET active = 1 WHERE email = ?";

        boolean deleted;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            deleted = ps.executeUpdate() != 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return deleted;
    }

}
