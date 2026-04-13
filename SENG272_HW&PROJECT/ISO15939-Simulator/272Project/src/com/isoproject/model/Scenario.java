package com.isoproject.model;
import java.util.ArrayList;

public class Scenario {
    private String name;
    private ArrayList<Dimension> dimensions = new ArrayList<>();

    public Scenario(String name) { this.name = name; }
    public void addDimension(Dimension d) { dimensions.add(d); }
    public String getName() { return name; }
    public ArrayList<Dimension> getDimensions() { return dimensions; }
}