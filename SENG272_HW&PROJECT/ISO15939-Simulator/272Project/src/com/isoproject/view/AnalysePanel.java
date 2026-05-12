package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends StepPanel {
    private JProgressBar pbOverall;
    private JLabel lblStatus;
    private JPanel detailsPanel;
    private JLabel lblDetail1;
    private JLabel lblDetail2;
    private JPanel gapPanel; // Boşluk Analizi için yeni panel
    private JLabel lblGapInfo;
    private JLabel lblGapWarning;

    public AnalysePanel() {
        super("Final Analysis", "STEP 5");
        initComponents();
    }

    private void initComponents() {
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JPanel overallPanel = new JPanel(new BorderLayout(10, 5));
        overallPanel.setOpaque(false);
        overallPanel.add(new JLabel("Overall Quality Score (1.0 - 5.0):"), BorderLayout.NORTH);

        pbOverall = new JProgressBar(10, 50);
        pbOverall.setValue(10);
        pbOverall.setStringPainted(true);
        pbOverall.setPreferredSize(new Dimension(300, 30));
        overallPanel.add(pbOverall, BorderLayout.CENTER);

        lblStatus = new JLabel("Status: -", JLabel.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        overallPanel.add(lblStatus, BorderLayout.SOUTH);

        detailsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Dimension Details"));

        lblDetail1 = new JLabel("   • Metric 1: -");
        lblDetail2 = new JLabel("   • Metric 2: -");
        detailsPanel.add(lblDetail1);
        detailsPanel.add(lblDetail2);

        gapPanel = new JPanel(new GridLayout(2, 1));
        gapPanel.setOpaque(false);
        gapPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(192, 57, 43)), "Gap Analysis"));

        lblGapInfo = new JLabel("Lowest Dimension: - | Gap: -", JLabel.CENTER);
        lblGapInfo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblGapInfo.setForeground(new Color(192, 57, 43));

        lblGapWarning = new JLabel("This dimension has the lowest score and requires the most improvement.", JLabel.CENTER);
        lblGapWarning.setFont(new Font("Segoe UI", Font.ITALIC, 12));

        gapPanel.add(lblGapInfo);
        gapPanel.add(lblGapWarning);

        centerPanel.add(overallPanel);
        centerPanel.add(detailsPanel);
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
        pbOverall.setString(String.format("%.1f / 5.0", overall));

        String qualityLabel;
        if (overall >= 4.0) {
            qualityLabel = "EXCELLENT";
            lblStatus.setForeground(new Color(39, 174, 96));
            pbOverall.setForeground(new Color(46, 204, 113));
        } else if (overall >= 3.0) {
            qualityLabel = "GOOD";
            lblStatus.setForeground(new Color(41, 128, 185));
            pbOverall.setForeground(new Color(52, 152, 219));
        } else if (overall >= 2.0) {
            qualityLabel = "NEEDS IMPROVEMENT";
            lblStatus.setForeground(new Color(243, 156, 18));
            pbOverall.setForeground(new Color(241, 196, 15));
        } else {
            qualityLabel = "POOR";
            lblStatus.setForeground(new Color(192, 57, 43));
            pbOverall.setForeground(new Color(231, 76, 60));
        }
        lblStatus.setText("Status: " + qualityLabel);

        String dim1Name, dim2Name;
        if ("Health".equals(SessionData.selectedMode)) {
            dim1Name = "Reliability (Uptime)";
            dim2Name = "Performance (Response Time)";
        } else {
            dim1Name = "Usability (SUS Score)";
            dim2Name = "Usability (Onboarding Time)";
        }

        lblDetail1.setText(String.format("   • %s: %.1f / 5.0", dim1Name, s1));
        lblDetail2.setText(String.format("   • %s: %.1f / 5.0", dim2Name, s2));

        double lowestScore = Math.min(s1, s2);
        String lowestDimName = (lowestScore == s1) ? dim1Name : dim2Name;
        double gapValue = 5.0 - lowestScore;

        lblGapInfo.setText(String.format("%s (Score: %.1f) | Gap Value: %.1f", lowestDimName, lowestScore, gapValue));
    }
}
