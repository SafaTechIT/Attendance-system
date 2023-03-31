package com.example.attendancesystem.database;

import com.example.attendancesystem.controller.User;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UserDriver extends Driver implements Serializable {

    private final ArrayList<User> users;
    private final Base64 base64;

    public UserDriver() {
        users = new ArrayList<>();
        base64 = new Base64(true);
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
            User result = new User(username, decrypt(password), name, role);
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

    private String encrypt(String password) {
        return base64.encodeAsString(password.getBytes(StandardCharsets.UTF_8));

    }

    private String decrypt(String password) {
        byte[] bytes = base64.decode(password);
        return new String(bytes);

    }
}
