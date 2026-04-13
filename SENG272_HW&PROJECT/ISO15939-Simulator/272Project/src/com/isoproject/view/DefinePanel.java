package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class DefinePanel extends StepPanel {
    private JRadioButton rbProduct, rbProcess;
    private JRadioButton rbEducation, rbHealth;
    private JComboBox<String> cbScenarios;
    private ButtonGroup typeGroup, modeGroup;

    public DefinePanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("Step 2: Define Measurement Scope", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel mainForm = new JPanel(new GridBagLayout());
        mainForm.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        mainForm.add(new JLabel("Quality Type:"), gbc);

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.setBackground(Color.WHITE);
        rbProduct = new JRadioButton("Product Quality");
        rbProcess = new JRadioButton("Process Quality");
        typeGroup = new ButtonGroup();
        typeGroup.add(rbProduct); typeGroup.add(rbProcess);
        typePanel.add(rbProduct); typePanel.add(rbProcess);

        gbc.gridx = 1;
        mainForm.add(typePanel, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        mainForm.add(new JLabel("Domain Mode:"), gbc);

        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        modePanel.setBackground(Color.WHITE);
        rbEducation = new JRadioButton("Education");
        rbHealth = new JRadioButton("Health");
        modeGroup = new ButtonGroup();
        modeGroup.add(rbEducation); modeGroup.add(rbHealth);
        modePanel.add(rbEducation); modePanel.add(rbHealth);

        gbc.gridx = 1;
        mainForm.add(modePanel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainForm.add(new JLabel("Select Scenario:"), gbc);

        String[] scenarios = {"Scenario A", "Scenario B", "Scenario C - Team Alpha"};
        cbScenarios = new JComboBox<>(scenarios);
        cbScenarios.setPreferredSize(new Dimension(250, 30));

        gbc.gridx = 1;
        mainForm.add(cbScenarios, gbc);

        add(mainForm, BorderLayout.CENTER);
    }

    @Override
    public boolean validateInput() {
        return (rbProduct.isSelected() || rbProcess.isSelected()) &&
                (rbEducation.isSelected() || rbHealth.isSelected());
    }

    @Override
    public void loadData() {
    }
}