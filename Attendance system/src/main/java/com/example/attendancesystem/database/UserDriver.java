package com.example.attendancesystem.database;

import com.example.attendancesystem.controller.User;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.tomcat.util.codec.binary.Base64;


public class UserDriver extends Driver {

    private final ArrayList<User> users;
    private SecretKey secretKey;
    private Cipher cipher;

    private final Base64 base64;

    public UserDriver() {
        base64 = new Base64(true);
        users = new ArrayList<>();
        this.get(getStatement());
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            secretKey = keyGenerator.generateKey();
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    protected void get(Statement st) {
        try {
            ResultSet rs = st.executeQuery("select * from users");
            String name;
            String username;
            String password;
            int role;
            while (rs.next()) {
                name = rs.getString("name");
                username = rs.getString("username");
                password = rs.getString("password");
                role = rs.getInt("role");
                User user = new User(name, username, password, role);
                users.add(user);
            }
            rs.close();
            closeAnything();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    private String encrypt(String password) {
        try {

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return base64.encodeAsString(cipher.doFinal(password.getBytes(StandardCharsets.UTF_8)));

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private String decrypt(String password) {
        byte[] bytes = base64.decode(password);
        try {

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(bytes));

        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
