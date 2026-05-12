package com.isoproject.main;

import com.isoproject.view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardsPanel = new JPanel(cardLayout);
    private int currentStep = 1;
    private final int TOTAL_STEPS = 5;

    private JPanel stepIndicatorBar;
    private ArrayList<JLabel> stepLabels = new ArrayList<>();

    public MainFrame() {
        setTitle("ISO 15939 Measurement Simulator - Zeynep Kara");
        setSize(1100, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        createStepIndicator();
        add(stepIndicatorBar, BorderLayout.NORTH);

        cardsPanel.add(new ProfilePanel(), "Step1");
        cardsPanel.add(new DefinePanel(), "Step2");
        cardsPanel.add(new PlanPanel(), "Step3");
        cardsPanel.add(new CollectPanel(), "Step4");
        cardsPanel.add(new AnalysePanel(), "Step5");
        add(cardsPanel, BorderLayout.CENTER);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 25, 15));
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(230, 230, 230)));

        JButton btnBack = new JButton("Back");
        JButton btnNext = new JButton("Next");

        btnNext.setPreferredSize(new Dimension(130, 45));
        btnNext.setBackground(new Color(41, 128, 185));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnNext.setFocusPainted(false);
        btnNext.setOpaque(true);
        btnNext.setBorderPainted(false);
        btnNext.setContentAreaFilled(true);

        btnNext.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btnNext.setBackground(new Color(52, 152, 219)); }
            public void mouseExited(MouseEvent e) { btnNext.setBackground(new Color(41, 128, 185)); }
        });

        btnBack.setPreferredSize(new Dimension(110, 45));
        btnBack.setBackground(new Color(245, 245, 245));
        btnBack.setFocusPainted(false);

        // İŞTE SİHİRLİ DOKUNUŞ BURADA:
        btnNext.addActionListener(e -> {
            boolean canProceed = true; // Geçişe izin var mı? Başlangıçta evet.

            // 1. Önce aktif olan (şu an ekranda görünen) paneli bul
            for (Component comp : cardsPanel.getComponents()) {
                if (comp.isVisible() && comp instanceof StepPanel) {
                    // 2. O panelin validateInput() metodunu çalıştır!
                    // Eğer false dönerse canProceed false olur ve geçiş durur.
                    if (!((StepPanel) comp).validateInput()) {
                        canProceed = false;
                        break;
                    }
                }
            }

            // 3. Eğer her şey tamamsa (canProceed true ise) sonraki sayfaya geç
            if (canProceed) {
                if (currentStep < TOTAL_STEPS) {
                    currentStep++;
                    cardLayout.show(cardsPanel, "Step" + currentStep);
                    updateStepIndicator();

                    // Yeni açılan panelin verilerini dinamik olarak yükle
                    for (Component comp : cardsPanel.getComponents()) {
                        if (comp.isVisible() && comp instanceof StepPanel) {
                            ((StepPanel) comp).loadData();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Measurement Simulation Completed!");
                }
            }
        });

        btnBack.addActionListener(e -> {
            if (currentStep > 1) {
                currentStep--;
                cardLayout.show(cardsPanel, "Step" + currentStep);
                updateStepIndicator();
            }
        });

        footer.add(btnBack);
        footer.add(btnNext);
        add(footer, BorderLayout.SOUTH);

        updateStepIndicator();
    }

    private void createStepIndicator() {
        stepIndicatorBar = new JPanel(new GridLayout(1, 5, 0, 0));
        stepIndicatorBar.setPreferredSize(new Dimension(0, 60));
        stepIndicatorBar.setBackground(new Color(245, 245, 245));

        String[] steps = {"Profile", "Define", "Plan", "Collect", "Analyse"};
        for (int i = 0; i < steps.length; i++) {
            JLabel lbl = new JLabel((i + 1) + ". " + steps[i], JLabel.CENTER);
            lbl.setOpaque(true);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setForeground(new Color(150, 150, 150));
            lbl.setBackground(new Color(245, 245, 245));
            lbl.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(220, 220, 220)));

            stepLabels.add(lbl);
            stepIndicatorBar.add(lbl);
        }
    }

    private void updateStepIndicator() {
        for (int i = 0; i < stepLabels.size(); i++) {
            if (i + 1 == currentStep) {
                stepLabels.get(i).setForeground(new Color(41, 128, 185));
                stepLabels.get(i).setBackground(Color.WHITE);
                stepLabels.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(41, 128, 185)));
            } else {
                stepLabels.get(i).setForeground(new Color(150, 150, 150));
                stepLabels.get(i).setBackground(new Color(245, 245, 245));
                stepLabels.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(220, 220, 220)));
            }
        }
    }

    public static void main(String[] args) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}