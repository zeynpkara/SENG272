package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class AnalysePanel extends StepPanel {
    private JProgressBar pbOverall;
    private JLabel lblStatus;
    private JPanel detailsPanel;

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

        pbOverall = new JProgressBar(10, 50);
        pbOverall.setValue(42); // Örnek puan: 4.2
        pbOverall.setStringPainted(true);
        pbOverall.setForeground(new Color(46, 204, 113)); // Başarılı yeşil
        pbOverall.setPreferredSize(new Dimension(300, 40));
        overallPanel.add(pbOverall, BorderLayout.CENTER);

        lblStatus = new JLabel("Status: EXCELLENT", JLabel.CENTER);
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblStatus.setForeground(new Color(39, 174, 96));
        overallPanel.add(lblStatus, BorderLayout.SOUTH);

        detailsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        detailsPanel.setBackground(new Color(245, 245, 245));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Dimension Details"));

        detailsPanel.add(new JLabel("   • Usability Score: 4.2 / 5.0"));
        detailsPanel.add(new JLabel("   • Performance Score: 3.8 / 5.0"));

        centerPanel.add(overallPanel);
        centerPanel.add(detailsPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    @Override public boolean validateInput() { return true; }

    @Override public void loadData() {
    }
}
