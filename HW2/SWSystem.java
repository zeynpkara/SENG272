import java.util.ArrayList;

public class SWSystem {

    private String name;
    private String category;
    private String version;

    private ArrayList<QualityDimension> dimensions;

    public SWSystem(String name, String category, String version) {

        this.name = name;
        this.category = category;
        this.version = version;

        dimensions = new ArrayList<>();
    }

    public void addDimension(QualityDimension d) {
        dimensions.add(d);
    }

    public ArrayList<QualityDimension> getDimensions() {
        return dimensions;
    }

    public double calculateOverallScore() {

        double total = 0;
        double weightSum = 0;

        for(QualityDimension d : dimensions) {

            total += d.calculateDimensionScore() * d.getWeight();
            weightSum += d.getWeight();
        }

        return total / weightSum;
    }

    public QualityDimension findWeakestDimension() {

        QualityDimension weakest = dimensions.get(0);

        for(QualityDimension d : dimensions) {

            if(d.calculateDimensionScore() <
                    weakest.calculateDimensionScore()) {

                weakest = d;
            }
        }

        return weakest;
    }

    public void printReport() {

        System.out.println("========================================");
        System.out.println("SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.println("System: " + name + " v" + version + " (" + category + ")");
        System.out.println("========================================");

        for(QualityDimension d : dimensions) {

            System.out.println("\n--- " + d.getName() +
                    " [" + d.getIsoCode() +
                    "] (Weight: " + d.getWeight() + ") ---");

            for(Criterion c : d.getCriteria()) {

                System.out.println(
                        c.getName() + ": " +
                                c.getMeasuredValue() + " " +
                                c.getUnit() +
                                " -> Score: " +
                                c.calculateScore() +
                                " (" + c.getDirection() + " is better)"
                );
            }

            System.out.println(
                    ">> Dimension Score: " +
                            d.calculateDimensionScore() +
                            "/5 [" + d.getQualityLabel() + "]"
            );
        }

        double overall = calculateOverallScore();

        System.out.println("\n========================================");
        System.out.println("OVERALL QUALITY SCORE: " +
                overall + "/5");
        System.out.println("========================================");

        QualityDimension weakest = findWeakestDimension();

        double gap = 5 - weakest.calculateDimensionScore();

        System.out.println("\nGAP ANALYSIS");
        System.out.println("========================================");

        System.out.println("Weakest Characteristic : "
                + weakest.getName()
                + " [" + weakest.getIsoCode() + "]");

        System.out.println("Score: "
                + weakest.calculateDimensionScore()
                + "/5  |  Gap: "
                + gap);

        System.out.println("Level: "
                + weakest.getQualityLabel());

        System.out.println(">> This characteristic requires the most improvement.");
    }
}