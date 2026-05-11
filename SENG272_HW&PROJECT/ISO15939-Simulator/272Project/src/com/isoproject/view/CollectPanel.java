package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class CollectPanel extends StepPanel {
    private JTextField txtMetric1;
    private JTextField txtMetric2;
    private JLabel lblMetric1Name;
    private JLabel lblMetric2Name;
    private JLabel lblMetric1Calculated;
    private JLabel lblMetric2Calculated;

    public CollectPanel() {
        super("Data Collection", "STEP 4");
        initComponents();
    }

    private void initComponents() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Enter Measurement Metrics");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        centerPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1;
        lblMetric1Name = new JLabel("Metric 1:");
        centerPanel.add(lblMetric1Name, gbc);
        txtMetric1 = new JTextField(10);
        gbc.gridx = 1; centerPanel.add(txtMetric1, gbc);
        lblMetric1Calculated = new JLabel("Score: -");
        gbc.gridx = 2; centerPanel.add(lblMetric1Calculated, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        lblMetric2Name = new JLabel("Metric 2:");
        centerPanel.add(lblMetric2Name, gbc);
        txtMetric2 = new JTextField(10);
        gbc.gridx = 1; centerPanel.add(txtMetric2, gbc);
        lblMetric2Calculated = new JLabel("Score: -");
        gbc.gridx = 2; centerPanel.add(lblMetric2Calculated, gbc);

        JButton btnCalculate = new JButton("Calculate Scores");
        btnCalculate.setBackground(new Color(41, 128, 185));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
        btnCalculate.setFocusPainted(false);

        btnCalculate.setOpaque(true);
        btnCalculate.setBorderPainted(false);

        btnCalculate.addActionListener(e -> calculateLocalScores());
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 10, 10, 10);
        centerPanel.add(btnCalculate, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void calculateLocalScores() {
        try {
            double val1 = Double.parseDouble(txtMetric1.getText());
            double val2 = Double.parseDouble(txtMetric2.getText());
            double s1 = 0, s2 = 0;

            if ("Health".equals(SessionData.selectedMode)) {
                s1 = 1.0 + ((val1 - 0) / (100 - 0)) * 4.0;
                s2 = 5.0 - ((val2 - 0) / (500 - 0)) * 4.0;
            } else {
                s1 = 1.0 + ((val1 - 0) / (100 - 0)) * 4.0;
                s2 = 5.0 - ((val2 - 0) / (60 - 0)) * 4.0;
            }

            double finalScore1 = Math.min(5.0, Math.max(1.0, s1));
            double finalScore2 = Math.min(5.0, Math.max(1.0, s2));

            lblMetric1Calculated.setText(String.format("Score: %.1f", finalScore1));
            lblMetric2Calculated.setText(String.format("Score: %.1f", finalScore2));

            SessionData.score1 = finalScore1;
            SessionData.score2 = finalScore2;

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!");
        }
    }

    @Override
    public boolean validateInput() {
        return !txtMetric1.getText().isEmpty() && !txtMetric2.getText().isEmpty();
    }

    @Override
    public void loadData() {
        if ("Health".equals(SessionData.selectedMode)) {
            lblMetric1Name.setText("Uptime (%):");
            lblMetric2Name.setText("Response Time (ms):");
        } else {
            lblMetric1Name.setText("SUS Score (0-100):");
            lblMetric2Name.setText("Onboarding Time (min):");
        }

        txtMetric1.setText("");
        txtMetric2.setText("");
        lblMetric1Calculated.setText("Score: -");
        lblMetric2Calculated.setText("Score: -");
    }
}
