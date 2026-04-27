package com.isoproject.view;
import javax.swing.*;
import java.awt.*;

public abstract class StepPanel extends JPanel {
    public StepPanel(String stepTitle, String stepNumber) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(new Color(236, 240, 241));
        rightPanel.setPreferredSize(new Dimension(250, 0));

        JLabel lblStep = new JLabel(stepNumber, JLabel.CENTER);
        lblStep.setFont(new Font("Segoe UI Black", Font.BOLD, 60));
        lblStep.setForeground(new Color(189, 195, 199));
        rightPanel.add(lblStep, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);
    }
    public abstract boolean validateInput();
    public abstract void loadData();
}
