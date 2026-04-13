package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class CollectPanel extends StepPanel {
    private JTextField txtSusScore;
    private JTextField txtOnboardingTime;
    private JLabel lblSusCalculated;
    private JLabel lblOnboardingCalculated;

    public CollectPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("Step 4: Data Collection & Scoring", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel mainForm = new JPanel(new GridBagLayout());
        mainForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        mainForm.add(new JLabel("SUS Score (0-100):"), gbc);
        gbc.gridx = 1;
        txtSusScore = new JTextField(10);
        mainForm.add(txtSusScore, gbc);
        gbc.gridx = 2;
        lblSusCalculated = new JLabel("Score: -");
        mainForm.add(lblSusCalculated, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainForm.add(new JLabel("Onboarding Time (min):"), gbc);
        gbc.gridx = 1;
        txtOnboardingTime = new JTextField(10);
        mainForm.add(txtOnboardingTime, gbc);
        gbc.gridx = 2;
        lblOnboardingCalculated = new JLabel("Score: -");
        mainForm.add(lblOnboardingCalculated, gbc);

        JButton btnCalculate = new JButton("Calculate Scores");
        btnCalculate.addActionListener(e -> calculateLocalScores());

        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBackground(Color.WHITE);
        centerWrapper.add(mainForm, BorderLayout.CENTER);
        centerWrapper.add(btnCalculate, BorderLayout.SOUTH);

        add(centerWrapper, BorderLayout.CENTER);
    }

    private void calculateLocalScores() {
        try {
            double sus = Double.parseDouble(txtSusScore.getText());
            double onboarding = Double.parseDouble(txtOnboardingTime.getText());

            double susScore = 1.0 + ((sus - 0) / (100 - 0)) * 4.0;
            double onbScore = 5.0 - ((onboarding - 0) / (60 - 0)) * 4.0; // Lower is better

            lblSusCalculated.setText(String.format("Score: %.1f", Math.min(5, Math.max(1, susScore))));
            lblOnboardingCalculated.setText(String.format("Score: %.1f", Math.min(5, Math.max(1, onbScore))));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Lütfen geçerli sayısal değerler girin!");
        }
    }

    @Override
    public boolean validateInput() {
        return !txtSusScore.getText().isEmpty() && !txtOnboardingTime.getText().isEmpty();
    }

    @Override
    public void loadData() {}
}