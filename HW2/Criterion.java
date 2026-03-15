public class Criterion {

    private String name;
    private double weight;
    private String direction;
    private double minValue;
    private double maxValue;
    private String unit;
    private double measuredValue;

    public Criterion(String name, double weight, String direction,
                     double minValue, double maxValue, String unit) {

        this.name = name;
        this.weight = weight;
        this.direction = direction;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.unit = unit;
    }

    public void setMeasuredValue(double measuredValue) {
        this.measuredValue = measuredValue;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public double getMeasuredValue() {
        return measuredValue;
    }

    public String getDirection() {
        return direction;
    }

    public double calculateScore() {

        double score;

        if(direction.equalsIgnoreCase("higher")) {

            score = 1 + (measuredValue - minValue) /
                    (maxValue - minValue) * 4;

        } else {

            score = 5 - (measuredValue - minValue) /
                    (maxValue - minValue) * 4;
        }

        if(score < 1) score = 1;
        if(score > 5) score = 5;

        score = Math.round(score * 2) / 2.0;

        return score;
    }
}