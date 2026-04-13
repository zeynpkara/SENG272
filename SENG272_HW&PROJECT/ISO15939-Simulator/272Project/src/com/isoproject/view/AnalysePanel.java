package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends StepPanel {
    private JProgressBar pbOverall;
    private JLabel lblStatus;
    private JPanel detailsPanel;

    public AnalysePanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("Step 5: Final Analysis & Evaluation", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel mainContent = new JPanel(new GridLayout(2, 1, 10, 20));
        mainContent.setBackground(Color.WHITE);
        mainContent.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JPanel overallPanel = new JPanel(new BorderLayout(10, 10));
        overallPanel.setBackground(Color.WHITE);
        overallPanel.add(new JLabel("Overall Measurement Score (1.0 - 5.0):"), BorderLayout.NORTH);

        pbOverall = new JProgressBar(10, 50);
        pbOverall.setValue(35);
        pbOverall.setStringPainted(true);
        pbOverall.setForeground(new Color(46, 204, 113));
        pbOverall.setPreferredSize(new Dimension(300, 40));
        overallPanel.add(pbOverall, BorderLayout.CENTER);

        lblStatus = new JLabel("Status: EXCELLENT", JLabel.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 16));
        overallPanel.add(lblStatus, BorderLayout.SOUTH);

        detailsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Dimension Details"));

        detailsPanel.add(new JLabel("• Usability: 4.2 / 5.0"));
        detailsPanel.add(new JLabel("• Performance: 3.8 / 5.0"));

        mainContent.add(overallPanel);
        mainContent.add(detailsPanel);

        add(mainContent, BorderLayout.CENTER);
    }

    @Override
    public boolean validateInput() { return true; }

    @Override
    public void loadData() {

    }
}