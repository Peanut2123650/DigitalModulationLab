package com.virtuallab.tutorial;

import javax.swing.*;
import java.awt.*;

public class UIUtils {
    public static final Color BG_COLOR = new Color(245, 248, 250);
    public static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    public static final Color ACCENT_COLOR = new Color(100, 149, 237);

    public static JButton createStyledButton(String text, int fontSize, Color textColor, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(ACCENT_COLOR);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }
}
