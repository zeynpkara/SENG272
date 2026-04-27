package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class CollectPanel extends StepPanel {
    private JTextField txtSusScore;
    private JTextField txtOnboardingTime;
    private JLabel lblSusCalculated;
    private JLabel lblOnboardingCalculated;

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
        centerPanel.add(new JLabel("SUS Score (0-100):"), gbc);
        txtSusScore = new JTextField(10);
        gbc.gridx = 1; centerPanel.add(txtSusScore, gbc);
        lblSusCalculated = new JLabel("Score: -");
        gbc.gridx = 2; centerPanel.add(lblSusCalculated, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Onboarding Time (min):"), gbc);
        txtOnboardingTime = new JTextField(10);
        gbc.gridx = 1; centerPanel.add(txtOnboardingTime, gbc);
        lblOnboardingCalculated = new JLabel("Score: -");
        gbc.gridx = 2; centerPanel.add(lblOnboardingCalculated, gbc);

        JButton btnCalculate = new JButton("Calculate Scores");
        btnCalculate.setBackground(new Color(41, 128, 185));
        btnCalculate.setForeground(Color.WHITE);
        btnCalculate.setFocusPainted(false);
        btnCalculate.addActionListener(e -> calculateLocalScores());

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.insets = new Insets(30, 10, 10, 10);
        centerPanel.add(btnCalculate, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void calculateLocalScores() {
        try {
            double sus = Double.parseDouble(txtSusScore.getText());
            double onboarding = Double.parseDouble(txtOnboardingTime.getText());

            double susScore = 1.0 + ((sus - 0) / (100 - 0)) * 4.0;
            double onbScore = 5.0 - ((onboarding - 0) / (60 - 0)) * 4.0;

            lblSusCalculated.setText(String.format("Score: %.1f", Math.min(5, Math.max(1, susScore))));
            lblOnboardingCalculated.setText(String.format("Score: %.1f", Math.min(5, Math.max(1, onbScore))));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!");
        }
    }

    @Override public boolean validateInput() { return !txtSusScore.getText().isEmpty(); }
    @Override public void loadData() {}
}
