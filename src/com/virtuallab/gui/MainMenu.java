package com.virtuallab.gui; // ✅ Updated package

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.virtuallab.tutorial.*;

public class MainMenu extends JFrame {
    private final Color bgColor = new Color(30, 30, 40);
    private final Color primaryColor = new Color(70, 130, 180);
    private final Color accentColor = new Color(100, 149, 237);

    public MainMenu() {
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(bgColor);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        JLabel titleLabel = new JLabel("Digital Modulation Virtual Lab");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Main Content Panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(bgColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Create and add buttons
        gbc.gridy = 0;
        contentPanel.add(createMenuButton("📖 Tutorials About Experiment", e -> {
            dispose();
            new TutorialPage().setVisible(true);
        }), gbc);

        gbc.gridy = 1;
        contentPanel.add(createMenuButton("⚙️ Perform Experiments", e -> {
            dispose();
            new LaboratoryGUI().setVisible(true);
        }), gbc);

        gbc.gridy = 2;
        contentPanel.add(createMenuButton("📜 Past Experiments", e -> {
            JOptionPane.showMessageDialog(this, "Feature coming soon!");
        }), gbc);

        gbc.gridy = 3;
        contentPanel.add(createMenuButton("🧠 Take a Quiz", e -> {
            dispose();
            new com.virtuallab.quiz.QuizPage().setVisible(true); // ✅ Updated reference
        }), gbc);

        // Add panels to frame
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JButton createMenuButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(300, 60));
        button.addActionListener(action);

        // Hover effects
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(accentColor);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
    }
}
