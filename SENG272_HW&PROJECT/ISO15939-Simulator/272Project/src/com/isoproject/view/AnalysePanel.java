package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends StepPanel {
    private JProgressBar pbOverall;
    private JLabel lblStatus;
    private JPanel detailsPanel;
    private JLabel lblDetail1;
    private JLabel lblDetail2;

    public AnalysePanel() {
        super("Final Analysis", "STEP 5");
        initComponents();
    }

    private void initComponents() {
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 20, 20));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JPanel overallPanel = new JPanel(new BorderLayout(10, 10));
        overallPanel.setOpaque(false);
        overallPanel.add(new JLabel("Overall Quality Score (1.0 - 5.0):"), BorderLayout.NORTH);

        pbOverall = new JProgressBar(10, 50); // Min 1.0 (10), Max 5.0 (50)
        pbOverall.setValue(10);
        pbOverall.setStringPainted(true);
        pbOverall.setPreferredSize(new Dimension(300, 40));
        overallPanel.add(pbOverall, BorderLayout.CENTER);

        lblStatus = new JLabel("Status: -", JLabel.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 18));
        overallPanel.add(lblStatus, BorderLayout.SOUTH);

        detailsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Dimension Details"));

        lblDetail1 = new JLabel("   • Metric 1: -");
        lblDetail2 = new JLabel("   • Metric 2: -");
        detailsPanel.add(lblDetail1);
        detailsPanel.add(lblDetail2);

        centerPanel.add(overallPanel);
        centerPanel.add(detailsPanel);

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

        if (overall >= 4.0) {
            lblStatus.setText("Status: EXCELLENT");
            lblStatus.setForeground(new Color(39, 174, 96)); // Yeşil
            pbOverall.setForeground(new Color(46, 204, 113));
        } else if (overall >= 3.0) {
            lblStatus.setText("Status: ACCEPTABLE");
            lblStatus.setForeground(new Color(243, 156, 18)); // Turuncu
            pbOverall.setForeground(new Color(241, 196, 15));
        } else {
            lblStatus.setText("Status: NEEDS IMPROVEMENT");
            lblStatus.setForeground(new Color(192, 57, 43)); // Kırmızı
            pbOverall.setForeground(new Color(231, 76, 60));
        }

        if ("Health".equals(SessionData.selectedMode)) {
            lblDetail1.setText(String.format("   • Reliability (Uptime): %.1f / 5.0", s1));
            lblDetail2.setText(String.format("   • Performance (Response Time): %.1f / 5.0", s2));
        } else {
            lblDetail1.setText(String.format("   • Usability (SUS Score): %.1f / 5.0", s1));
            lblDetail2.setText(String.format("   • Usability (Onboarding Time): %.1f / 5.0", s2));
        }
    }
}
