package com.example.happinessproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryHandler {
    public void getHighestEconomyCountry(Connection connection) throws SQLException {
        String sql = "SELECT name FROM Country WHERE economy = (SELECT MAX(economy) FROM Country WHERE region IN ('Latin America and Caribbean', 'Eastern Asia'))";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                System.out.println("Страна с самым высоким показателем экономики: " + rs.getString("name"));
            }
        }
    }

    public void getAverageCountry(Connection connection) throws SQLException {
        String sql = "SELECT name FROM Country WHERE region IN ('Western Europe', 'North America') AND " +
                "ABS(happiness_score - (SELECT AVG(happiness_score) FROM Country)) < 0.1";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("Средняя страна: " + rs.getString("name"));
            }
        }
    }
}
