package com.virtuallab.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonsPanel extends JPanel {
    public JButton resultButton, resetButton, demodulateButton, backButton;

    public ButtonsPanel() {
        // Set GridBagLayout for flexible placement of buttons
        setLayout(new GridBagLayout());
        setBackground(new Color(21, 43, 89)); // Deep Navy Blue

        // Create a GridBagConstraints object to manage layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Spacing between buttons
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create buttons with custom styles
        resultButton = createStyledButton("Show Result");
        resetButton = createStyledButton("Reset");
        demodulateButton = createStyledButton("Demodulate");
        backButton = createStyledButton("Back to Main Menu");

        // Adjust "Back" button style (Red color for prominence)
        backButton.setBackground(new Color(192, 57, 43)); // Red
        backButton.setForeground(Color.WHITE);

        // Arrange the buttons in a row with centered alignment
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        add(resultButton, gbc);

        gbc.gridx = 1;
        add(resetButton, gbc);

        gbc.gridx = 2;
        add(demodulateButton, gbc);

        gbc.gridx = 3;
        gbc.gridwidth = 2;  // Make the back button span wider
        add(backButton, gbc);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        button.setPreferredSize(new Dimension(150, 40));

        // Add hover effect to change button background on mouse events
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 160, 220)); // Lighter blue
            }

            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Back to original color
            }
        });

        return button;
    }
}
