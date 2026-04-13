package com.isoproject.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlanPanel extends StepPanel {
    private JTable planTable;
    private DefaultTableModel tableModel;

    public PlanPanel() {
        super();
        initComponents();
    }

    private void initComponents() {
        JLabel lblTitle = new JLabel("Step 3: Measurement Plan", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"Dimension", "Metric", "Weight/Coeff", "Direction", "Unit", "Range"};
        tableModel = new DefaultTableModel(columns, 0);
        planTable = new JTable(tableModel);

        planTable.setRowHeight(30);
        planTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(planTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);

        JLabel lblInfo = new JLabel("Please review the measurement structure before data collection.", JLabel.CENTER);
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        add(lblInfo, BorderLayout.SOUTH);
    }

    @Override
    public boolean validateInput() {
        return true;
    }

    @Override
    public void loadData() {
        tableModel.setRowCount(0);
        // FAKE DATA
        tableModel.addRow(new Object[]{"Usability", "SUS Score", "0.50", "Higher↑", "Points", "0-100"});
        tableModel.addRow(new Object[]{"Usability", "Onboarding Time", "0.50", "Lower↓", "Minutes", "0-60"});
        tableModel.addRow(new Object[]{"Performance", "Response Time", "1.00", "Lower↓", "ms", "0-500"});
    }
}