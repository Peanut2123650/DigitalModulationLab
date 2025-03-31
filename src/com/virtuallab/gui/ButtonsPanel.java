package com.virtuallab.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonsPanel extends JPanel {
    public JButton resultButton, resetButton, demodulateButton, backButton;

    public ButtonsPanel() {
        setLayout(new FlowLayout());

        // Create buttons
        resultButton = createButton("Show Result");
        resetButton = createButton("Reset");
        demodulateButton = createButton("Demodulate");
        backButton = createButton("Back to com.virtuallab.Main Menu"); // Back button added

        add(resultButton);
        add(resetButton);
        add(demodulateButton);
        add(backButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
}