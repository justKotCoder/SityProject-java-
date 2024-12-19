package com.example.happinessproject;

public class Country {
    private String name;
    private String region;
    private int happinessRank;
    private double happinessScore;
    private double economy;

    public Country(String name, String region, int happinessRank, double happinessScore, double economy) {
        this.name = name;
        this.region = region;
        this.happinessRank = happinessRank;
        this.happinessScore = happinessScore;
        this.economy = economy;
    }

    public String getName() { return name; }
    public String getRegion() { return region; }
    public int getHappinessRank() { return happinessRank; }
    public double getHappinessScore() { return happinessScore; }
    public double getEconomy() { return economy; }
}
