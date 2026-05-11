package com.isoproject.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanPanel extends StepPanel {
    private JTable planTable;
    private DefaultTableModel tableModel;

    public PlanPanel() {
        super("Measurement Plan", "STEP 3");
        initComponents();
    }

    private void initComponents() {
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitle = new JLabel("Measurement Plan Structure");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        centerPanel.add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"Dimension", "Metric", "Weight", "Direction", "Unit", "Range"};
        tableModel = new DefaultTableModel(columns, 0);
        planTable = new JTable(tableModel);

        planTable.setRowHeight(35);
        planTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        planTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        planTable.getTableHeader().setBackground(new Color(236, 240, 241));

        JScrollPane scrollPane = new JScrollPane(planTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("Please review the measurement structure before data collection.", JLabel.CENTER);
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfo.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        centerPanel.add(lblInfo, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
    }

    @Override
    public boolean validateInput() {
        return true;
    }

    @Override
    public void loadData() {
        tableModel.setRowCount(0);

        if ("Health".equals(SessionData.selectedMode)) {
            tableModel.addRow(new Object[]{"Reliability", "Uptime (%)", "0.60", "Higher↑", "%", "0-100"});
            tableModel.addRow(new Object[]{"Performance", "Response Time (ms)", "0.40", "Lower↓", "ms", "0-500"});
        } else {
            tableModel.addRow(new Object[]{"Usability", "SUS Score", "0.50", "Higher↑", "Points", "0-100"});
            tableModel.addRow(new Object[]{"Usability", "Onboarding Time", "0.50", "Lower↓", "Minutes", "0-60"});
        }
    }
}
