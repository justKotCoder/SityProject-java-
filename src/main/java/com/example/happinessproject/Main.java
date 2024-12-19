package com.example.happinessproject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import com.opencsv.exceptions.CsvValidationException;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "src/main/resources/sity.csv";
        String dbName = "happiness.db";

        try {
            // 1. Парсинг CSV
            CSVParser parser = new CSVParser();
            List<Country> countries = parser.parse(csvFilePath);

            // 2. Работа с базой данных
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.connect(dbName);
            dbHandler.createTables();

            // 3. Вставка данных в БД
            for (Country country : countries) {
                dbHandler.insertCountry(country);
            }

            // 4. Выполнение запросов
            QueryHandler queryHandler = new QueryHandler();
            queryHandler.getHighestEconomyCountry(dbHandler.getConnection());
            queryHandler.getAverageCountry(dbHandler.getConnection());

            dbHandler.close();
        } catch (IOException | SQLException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
