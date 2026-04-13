package com.isoproject.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JButton btnNext, btnBack;
    private int currentStep = 1;
    private final int TOTAL_STEPS = 5;

    public MainFrame() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        cardsPanel.add(new ProfilePanel(), "Step1");
        cardsPanel.add(new DefinePanel(), "Step2");
        cardsPanel.add(new PlanPanel(), "Step3");
        cardsPanel.add(new CollectPanel(), "Step4");
        cardsPanel.add(new AnalysePanel(), "Step5");
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        footerPanel.setBackground(new Color(245, 245, 245));

        btnBack = new JButton("Back");
        btnNext = new JButton("Next");

        btnNext.setPreferredSize(new Dimension(100, 35));
        btnBack.setPreferredSize(new Dimension(100, 35));

        JPanel buttonGroup = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonGroup.setOpaque(false);
        buttonGroup.add(btnBack);
        buttonGroup.add(btnNext);

        footerPanel.add(buttonGroup, BorderLayout.EAST);

        btnNext.addActionListener(e -> {
            if (currentStep < TOTAL_STEPS) {
                currentStep++;
                cardLayout.show(cardsPanel, "Step" + currentStep);
                updateButtons();
            } else if (currentStep == TOTAL_STEPS) {
                JOptionPane.showMessageDialog(this, "Session Finished Successfully!");
            }
        });

        btnBack.addActionListener(e -> {
            if (currentStep > 1) {
                currentStep--;
                cardLayout.show(cardsPanel, "Step" + currentStep);
                updateButtons();
            }
        });

        add(cardsPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        updateButtons();
    }

    private void updateButtons() {
        btnBack.setEnabled(currentStep > 1);
        btnNext.setText(currentStep == TOTAL_STEPS ? "Finish" : "Next");
    }

    private JPanel createPlaceholderPanel(String text) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(Color.WHITE);
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.ITALIC, 20));
        p.add(lbl);
        return p;
    }

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}