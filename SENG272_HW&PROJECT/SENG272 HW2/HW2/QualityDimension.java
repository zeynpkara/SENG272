import java.util.ArrayList;

public class QualityDimension {

    private String name;
    private String isoCode;
    private double weight;

    private ArrayList<Criterion> criteria;

    public QualityDimension(String name, String isoCode, double weight) {

        this.name = name;
        this.isoCode = isoCode;
        this.weight = weight;

        criteria = new ArrayList<>();
    }

    public void addCriterion(Criterion c) {
        criteria.add(c);
    }

    public ArrayList<Criterion> getCriteria() {
        return criteria;
    }

    public String getName() {
        return name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public double getWeight() {
        return weight;
    }

    public double calculateDimensionScore() {

        double total = 0;
        double weightSum = 0;

        for(Criterion c : criteria) {

            total += c.calculateScore() * c.getWeight();
            weightSum += c.getWeight();
        }

        return total / weightSum;
    }

    public String getQualityLabel() {

        double score = calculateDimensionScore();

        if(score >= 4.5)
            return "Excellent Quality";

        if(score >= 3.5)
            return "Good Quality";

        if(score >= 2.5)
            return "Needs Improvement";

        return "Poor Quality";
    }
}