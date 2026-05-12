package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends StepPanel {
    private JTextField txtUsername;
    private JTextField txtSchool;
    private JTextField txtSessionName;

    public ProfilePanel() {
        super("User Profile", "STEP 1");
        initComponents();
    }

    private void initComponents() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setBackground(new Color(41, 128, 185));
        headerBar.setPreferredSize(new Dimension(0, 60));
        JLabel lblHeader = new JLabel("  ISO 15939 SIMULATOR", JLabel.LEFT);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        headerBar.add(lblHeader, BorderLayout.WEST);
        contentPanel.add(headerBar, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblFormTitle = new JLabel("User Profile Details");
        lblFormTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblFormTitle.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        formPanel.add(lblFormTitle, gbc);

        gbc.gridwidth = 1;
        addStyledField(formPanel, "Username", txtUsername = new JTextField(20), gbc, 1);
        addStyledField(formPanel, "School/Organization", txtSchool = new JTextField(20), gbc, 2);
        addStyledField(formPanel, "Session Name", txtSessionName = new JTextField(20), gbc, 3);

        contentPanel.add(formPanel, BorderLayout.CENTER);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addStyledField(JPanel p, String label, JTextField tf, GridBagConstraints gbc, int y) {
        gbc.gridy = y;
        gbc.gridx = 0;
        p.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        tf.setPreferredSize(new Dimension(250, 40));
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        p.add(tf, gbc);
    }

    @Override
    public boolean validateInput() {
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your username to continue.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtSchool.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter your school or organization to continue.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtSessionName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a session name to continue.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    @Override
    public void loadData() {}
}
