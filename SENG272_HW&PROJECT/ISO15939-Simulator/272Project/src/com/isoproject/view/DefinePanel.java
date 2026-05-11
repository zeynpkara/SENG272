package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class DefinePanel extends StepPanel {
    private JRadioButton rbProduct, rbProcess;
    private JRadioButton rbEducation, rbHealth;
    private JComboBox<String> cbScenarios;
    private ButtonGroup typeGroup, modeGroup;

    public DefinePanel() {
        super("Define Scope", "STEP 2");
        initComponents();
    }

    private void initComponents() {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblTitle = new JLabel("Measurement Scope Definition");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 30, 10);
        centerPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Quality Type Selection
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Quality Type:"), gbc);
        rbProduct = new JRadioButton("Product");
        rbProcess = new JRadioButton("Process");
        rbProduct.setOpaque(false); rbProcess.setOpaque(false);
        typeGroup = new ButtonGroup();
        typeGroup.add(rbProduct); typeGroup.add(rbProcess);
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p1.setOpaque(false);
        p1.add(rbProduct); p1.add(rbProcess);
        gbc.gridx = 1; centerPanel.add(p1, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Domain Mode:"), gbc);
        rbEducation = new JRadioButton("Education");
        rbHealth = new JRadioButton("Health");

        rbEducation.setSelected(true);

        rbEducation.setOpaque(false); rbHealth.setOpaque(false);
        modeGroup = new ButtonGroup();
        modeGroup.add(rbEducation); modeGroup.add(rbHealth);

        rbEducation.addActionListener(e -> SessionData.selectedMode = "Education");
        rbHealth.addActionListener(e -> SessionData.selectedMode = "Health");

        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        p2.setOpaque(false);
        p2.add(rbEducation); p2.add(rbHealth);
        gbc.gridx = 1; centerPanel.add(p2, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        centerPanel.add(new JLabel("Select Scenario:"), gbc);
        String[] scenarios = {"Scenario A (Education)", "Scenario B (Health)", "Scenario C (Usability)"};
        cbScenarios = new JComboBox<>(scenarios);
        gbc.gridx = 1; centerPanel.add(cbScenarios, gbc);

        add(centerPanel, BorderLayout.CENTER);
    }

    @Override public boolean validateInput() { return true; }
    @Override public void loadData() {}
}
