package com.virtuallab.quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIUtils {
    private static final Color BG_COLOR = new Color(245, 245, 250);
    private static final Color PRIMARY_COLOR = new Color(70, 130, 180);
    private static final Color ACCENT_COLOR = new Color(100, 149, 237);
    private static final Color DARK_TEXT_COLOR = new Color(60, 60, 60);

    public static JButton createStyledButton(String text, int fontSize) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, fontSize));
        button.setBackground(PRIMARY_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(ACCENT_COLOR);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    public static JRadioButton createStyledRadioButton() {
        JRadioButton radio = new JRadioButton();
        radio.setFont(new Font("Arial", Font.PLAIN, 16));
        radio.setBackground(BG_COLOR);
        radio.setForeground(DARK_TEXT_COLOR);
        radio.setFocusPainted(false);
        radio.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        radio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                radio.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });

        return radio;
    }
}
