package com.example.attendancesystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Driver {
    private Connection connection;
    private Statement statement;

    public Driver() {
        connector();
    }

    private void connector() {
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

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    protected abstract void get(Statement st);

    protected void closeAnything() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
