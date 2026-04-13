package com.isoproject.view;

import javax.swing.*;
import java.awt.*;


public abstract class StepPanel extends JPanel {

    public StepPanel() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
    }


    public abstract boolean validateInput();


    public abstract void loadData();
}