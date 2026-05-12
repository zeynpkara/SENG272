package com.isoproject.view;

import com.isoproject.model.SessionData;
import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends StepPanel {
    private JProgressBar pbOverall;
    private JLabel lblStatus;
    private JLabel lblScoreNumber; 
    private JPanel detailsPanel;
    private JLabel lblDetail1;
    private JLabel lblDetail2;
    private JPanel gapPanel;
    private JLabel lblGapInfo;
    private JLabel lblGapWarning;
    private BarChartPanel chartPanel;

    public AnalysePanel() {
        super("Final Analysis", "STEP 5");
        initComponents();
    }

    private void initComponents() {
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));


        JPanel overallPanel = new JPanel(new BorderLayout(5, 5));
        overallPanel.setOpaque(false);
        overallPanel.setMaximumSize(new Dimension(800, 130));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Overall Quality Score:");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(44, 62, 80));

        lblScoreNumber = new JLabel("0.0 / 5.0", JLabel.RIGHT);
        lblScoreNumber.setFont(new Font("Segoe UI", Font.BOLD, 26));

        titlePanel.add(lblTitle, BorderLayout.WEST);
        titlePanel.add(lblScoreNumber, BorderLayout.EAST);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));

        overallPanel.add(titlePanel, BorderLayout.NORTH);

        pbOverall = new JProgressBar(10, 50);
        pbOverall.setValue(10);
        pbOverall.setStringPainted(false); // YAZIYI KALDIRDIK!
        pbOverall.setPreferredSize(new Dimension(400, 20)); // Daha zarif bir kalınlık
        overallPanel.add(pbOverall, BorderLayout.CENTER);

        lblStatus = new JLabel("Status: -", JLabel.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblStatus.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        overallPanel.add(lblStatus, BorderLayout.SOUTH);


        JPanel middlePanel = new JPanel(new GridLayout(1, 2, 20, 0));
        middlePanel.setOpaque(false);
        middlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        detailsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        detailsPanel.setBackground(new Color(250, 250, 250));
        detailsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Dimension Details"),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));

        lblDetail1 = new JLabel();
        lblDetail2 = new JLabel();
        detailsPanel.add(lblDetail1);
        detailsPanel.add(lblDetail2);

        chartPanel = new BarChartPanel();
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), "Performance Graph"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        chartPanel.setBackground(Color.WHITE);

        middlePanel.add(detailsPanel);
        middlePanel.add(chartPanel);


        gapPanel = new JPanel(new GridLayout(2, 1));
        gapPanel.setBackground(new Color(253, 237, 236));
        gapPanel.setMaximumSize(new Dimension(800, 80));
        gapPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(231, 76, 60), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        lblGapInfo = new JLabel("Lowest Dimension: - | Gap: -", JLabel.CENTER);
        lblGapInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblGapInfo.setForeground(new Color(192, 57, 43));

        lblGapWarning = new JLabel("This dimension has the lowest score and requires the most improvement.", JLabel.CENTER);
        lblGapWarning.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblGapWarning.setForeground(new Color(192, 57, 43));

        gapPanel.add(lblGapInfo);
        gapPanel.add(lblGapWarning);

        centerPanel.add(overallPanel);
        centerPanel.add(middlePanel);
        centerPanel.add(gapPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    @Override public boolean validateInput() { return true; }

    @Override public void loadData() {
        double s1 = SessionData.score1;
        double s2 = SessionData.score2;
        double overall = (s1 + s2) / 2.0;

        int progressValue = (int) Math.round(overall * 10);
        pbOverall.setValue(progressValue);

        lblScoreNumber.setText(String.format("%.1f / 5.0", overall));

        String qualityLabel;
        if (overall >= 4.0) {
            qualityLabel = "EXCELLENT";
            lblStatus.setForeground(new Color(39, 174, 96));
            lblScoreNumber.setForeground(new Color(39, 174, 96));
            pbOverall.setForeground(new Color(46, 204, 113));
        } else if (overall >= 3.0) {
            qualityLabel = "GOOD";
            lblStatus.setForeground(new Color(41, 128, 185));
            lblScoreNumber.setForeground(new Color(41, 128, 185));
            pbOverall.setForeground(new Color(52, 152, 219));
        } else if (overall >= 2.0) {
            qualityLabel = "NEEDS IMPROVEMENT";
            lblStatus.setForeground(new Color(243, 156, 18));
            lblScoreNumber.setForeground(new Color(243, 156, 18));
            pbOverall.setForeground(new Color(241, 196, 15));
        } else {
            qualityLabel = "POOR";
            lblStatus.setForeground(new Color(192, 57, 43));
            lblScoreNumber.setForeground(new Color(192, 57, 43));
            pbOverall.setForeground(new Color(231, 76, 60));
        }
        lblStatus.setText("Status: " + qualityLabel);

        String dim1Name, dim2Name;
        if ("Health".equals(SessionData.selectedMode)) {
            dim1Name = "Reliability";
            dim2Name = "Performance";
        } else {
            dim1Name = "Usability (SUS)";
            dim2Name = "Usability (Onboarding)";
        }

        String htmlFormat = "<html><div style='padding: 5px; font-family: Segoe UI;'><b style='font-size:13px; color:#34495e;'>%s</b><br><span style='font-size:24px; color:#2980b9;'><b>%.1f</b></span> <span style='font-size:13px; color:#7f8c8d;'>/ 5.0</span></div></html>";
        lblDetail1.setText(String.format(htmlFormat, dim1Name, s1));
        lblDetail2.setText(String.format(htmlFormat, dim2Name, s2));

        chartPanel.updateChart(s1, s2, dim1Name, dim2Name);

        double lowestScore = Math.min(s1, s2);
        String lowestDimName = (lowestScore == s1) ? dim1Name : dim2Name;
        double gapValue = 5.0 - lowestScore;

        lblGapInfo.setText(String.format("%s (Score: %.1f)  |  Gap Value: %.1f", lowestDimName, lowestScore, gapValue));
    }

    class BarChartPanel extends JPanel {
        private double val1 = 0, val2 = 0;
        private String name1 = "", name2 = "";

        public void updateChart(double v1, double v2, String n1, String n2) {
            this.val1 = v1;
            this.val2 = v2;
            this.name1 = n1;
            this.name2 = n2;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int maxBarWidth = width - 100;

            int bar1Width = (int) ((val1 / 5.0) * maxBarWidth);
            g2.setColor(Color.DARK_GRAY);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            g2.drawString(name1, 15, 30);

            g2.setColor(new Color(52, 152, 219));
            g2.fillRoundRect(15, 40, bar1Width, 22, 8, 8); 

            g2.setColor(Color.BLACK);
            g2.drawString(String.format("%.1f", val1), 25 + bar1Width, 55);

            int bar2Width = (int) ((val2 / 5.0) * maxBarWidth);
            g2.setColor(Color.DARK_GRAY);
            g2.drawString(name2, 15, 90);

            g2.setColor(new Color(46, 204, 113));
            g2.fillRoundRect(15, 100, bar2Width, 22, 8, 8);

            g2.setColor(Color.BLACK);
            g2.drawString(String.format("%.1f", val2), 25 + bar2Width, 115);
        }
    }
}
