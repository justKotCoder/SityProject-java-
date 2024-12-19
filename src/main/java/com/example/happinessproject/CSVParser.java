package com.example.happinessproject;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {
    public List<Country> parse(String filePath) throws IOException, CsvValidationException {
        List<Country> countries = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] nextLine;
            reader.readNext(); // Пропустить заголовок
            while ((nextLine = reader.readNext()) != null) {
                countries.add(new Country(
                        nextLine[0], // Country
                        nextLine[1], // Region
                        Integer.parseInt(nextLine[2]), // Happiness Rank
                        Double.parseDouble(nextLine[3]), // Happiness Score
                        Double.parseDouble(nextLine[5])  // Economy
                ));
            }
        }
        return countries;
    }

}
