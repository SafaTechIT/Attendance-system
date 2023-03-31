package com.example.attendancesystem.database;

import java.sql.*;

public abstract class Driver {
    private Connection connection;
    private Statement statement;

    public Driver() {
        connector();
    }

    protected void connector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db",
                    "root",
                    "root");

            statement = connection.createStatement();
        } catch (SQLException exception) {
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Statement getStatement() {
        return statement;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected abstract void getAllObjects(Statement st);

    protected abstract Object getObjects(Statement st, int id);

    public abstract boolean insert(Object obj);

    public abstract boolean update(int id, Object obj);

    public abstract boolean delete(int id);

    protected boolean deleteItem(String sql, int id) {
        try {
            connector();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            closeAnything();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected void closeAnything() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
