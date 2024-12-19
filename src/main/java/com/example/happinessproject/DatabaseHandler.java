package com.example.happinessproject;

import java.sql.*;

public class DatabaseHandler {
    private Connection connection;

    public void connect(String dbName) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // Регистрация драйвера
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQLite Driver not found", e);
        }
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbName); // Подключение к БД
    }



    public void createTables() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Country (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "region TEXT, " +
                "happiness_rank INTEGER, " +
                "happiness_score REAL, " +
                "economy REAL)";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    public void insertCountry(Country country) throws SQLException {
        String insertSQL = "INSERT INTO Country (name, region, happiness_rank, happiness_score, economy) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, country.getName());
            pstmt.setString(2, country.getRegion());
            pstmt.setInt(3, country.getHappinessRank());
            pstmt.setDouble(4, country.getHappinessScore());
            pstmt.setDouble(5, country.getEconomy());
            pstmt.executeUpdate();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null) connection.close();
    }
}
