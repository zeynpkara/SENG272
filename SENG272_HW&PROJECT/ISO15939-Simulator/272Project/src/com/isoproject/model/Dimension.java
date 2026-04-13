package com.isoproject.model;
import java.util.ArrayList;

public class Dimension {
    private String name;
    private double weight;
    private ArrayList<Metric> metrics = new ArrayList<>();

    public Dimension(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public void addMetric(Metric m) { metrics.add(m); }

    public double calculateWeightedScore() {
        double totalScore = 0, totalCoeff = 0;
        for (Metric m : metrics) {
            totalScore += (m.getCalculatedScore() * m.getCoefficient());
            totalCoeff += m.getCoefficient();
        }
        return totalCoeff == 0 ? 0 : totalScore / totalCoeff;
    }

    public String getName() { return name; }
    public ArrayList<Metric> getMetrics() { return metrics; }
}