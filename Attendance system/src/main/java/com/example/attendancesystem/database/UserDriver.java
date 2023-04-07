package com.example.attendancesystem.database;

import com.example.attendancesystem.controller.User;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDriver extends Driver implements Serializable {

    private final ArrayList<User> users;

    public UserDriver() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        getAllObjects(this.getStatement());
        return users;
    }

    public User getUser(int id) {
        return (User) getObjects(this.getStatement(), id);
    }

    @Override
    protected void getAllObjects(Statement st) {
        try {
            ResultSet rs = st.executeQuery("select * from users");
            while (rs.next()) {
                User user = getUserData(rs);
                users.add(user);
            }
            rs.close();
            closeAnything();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    protected Object getObjects(Statement st, int id) {
        try {
            connector();
            String sql = "SELECT * FROM users WHERE id = " + id;
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            User result = getUserData(rs);
            rs.close();
            closeAnything();
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private User getUserData(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String username = rs.getString("username");
            String password = rs.getString("password");
            int role = rs.getInt("role");
            User result = new User(username, password, name, role);
            result.setId(id);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insert(Object obj) {
        User user = (User) obj;
        String username = user.getUsername();
        String password = encrypt(user.getHashMap().get(username));
        String name = user.getName();
        int role = user.getRole();
        String sql = "INSERT INTO users (name, username, password, role)" +
                "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, username);
            ps.setString(3, password);
            ps.setInt(4, role);
            ps.executeUpdate();
            closeAnything();
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(int id, Object obj) {
        User user = (User) obj;
        if (id == user.getId()) {
            String username = user.getUsername();
            String password = encrypt(user.getHashMap().get(username));
            String name = user.getName();
            int role = user.getRole();
            String sql = "UPDATE users SET name = ?, username = ?, password = ?, role = ? WHERE id=?";
            try {
                connector();
                PreparedStatement ps = getConnection().prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setInt(4, role);
                ps.setInt(5, id);
                ps.executeUpdate();
                closeAnything();
            } catch (SQLException exception) {
                exception.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        return deleteItem(sql, id);
    }

    public static String encrypt(String password) {
        StringBuilder result;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            result = new StringBuilder(no.toString(16));
            while (result.length() < 32) {
                result.insert(0, "0");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result.toString();

    }
}
