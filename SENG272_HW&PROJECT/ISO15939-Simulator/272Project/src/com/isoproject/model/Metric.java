package com.isoproject.model;

public class Metric {
    private String name;
    private double coefficient;
    private boolean isHigherBetter;
    private double minRange;
    private double maxRange;
    private String unit;
    private double rawValue;
    private double calculatedScore;

    public Metric(String name, double coefficient, boolean isHigherBetter, double minRange, double maxRange, String unit) {
        this.name = name;
        this.coefficient = coefficient;
        this.isHigherBetter = isHigherBetter;
        this.minRange = minRange;
        this.maxRange = maxRange;
        this.unit = unit;
    }

    public void calculateScore(double value) {
        this.rawValue = value;
        double score = isHigherBetter ?
                1.0 + ((value - minRange) / (maxRange - minRange)) * 4.0 :
                5.0 - ((value - minRange) / (maxRange - minRange)) * 4.0;

        this.calculatedScore = Math.round(Math.max(1.0, Math.min(5.0, score)) * 2.0) / 2.0;
    }

    public String getName() { return name; }
    public double getCoefficient() { return coefficient; }
    public double getCalculatedScore() { return calculatedScore; }
}