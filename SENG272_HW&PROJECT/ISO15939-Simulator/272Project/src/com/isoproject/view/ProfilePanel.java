package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends StepPanel {
    private JTextField txtUsername;
    private JTextField txtSchool;
    private JTextField txtSessionName;

    public ProfilePanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("Step 1: Enter Profile Information", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80)); // Koyu lacivert/gri tonu
        lblTitle.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel formWrapper = new JPanel(new GridBagLayout()); // Daha esnek kontrol sağlar
        formWrapper.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Elemanlar arası boşluk
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.PLAIN, 14);

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lbl1 = new JLabel("Username:");
        lbl1.setFont(labelFont);
        formWrapper.add(lbl1, gbc);

        gbc.gridx = 1;
        txtUsername = new JTextField(20);
        txtUsername.setPreferredSize(new Dimension(250, 35));
        formWrapper.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        JLabel lbl2 = new JLabel("School/Organization:");
        lbl2.setFont(labelFont);
        formWrapper.add(lbl2, gbc);

        gbc.gridx = 1;
        txtSchool = new JTextField(20);
        txtSchool.setPreferredSize(new Dimension(250, 35));
        formWrapper.add(txtSchool, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel lbl3 = new JLabel("Session Name:");
        lbl3.setFont(labelFont);
        formWrapper.add(lbl3, gbc);

        gbc.gridx = 1;
        txtSessionName = new JTextField(20);
        txtSessionName.setPreferredSize(new Dimension(250, 35));
        formWrapper.add(txtSessionName, gbc);

        add(formWrapper, BorderLayout.CENTER);
    }

    @Override public boolean validateInput() { return !txtUsername.getText().isEmpty(); }
    @Override public void loadData() {}
}