package com.virtuallab.gui;

import com.virtuallab.auth.UserSession;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JFrame {
    // 🎨 Updated Color Palette (Matching the Theme)
    private final Color bgColor = new Color(21, 43, 89); // Deep Navy Blue
    private final Color primaryColor = new Color(70, 130, 180); // Soft Blue
    private final Color accentColor = new Color(160, 195, 217); // Soft Azure Blue
    private final Color textColor = Color.WHITE;

    public MainMenu() {
        setTitle("Digital Modulation Virtual Lab");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(bgColor);

        // 🔹 HEADER PANEL (IMPROVED DESIGN)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(bgColor);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel titleLabel = new JLabel("Digital Modulation Lab", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        titleLabel.setForeground(textColor);

        JLabel subtitleLabel = new JLabel("Interactive Virtual Laboratory", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitleLabel.setForeground(accentColor);

        // Adding title and subtitle inside a container
        JPanel textContainer = new JPanel(new GridLayout(2, 1));
        textContainer.setBackground(bgColor);
        textContainer.add(titleLabel);
        textContainer.add(subtitleLabel);

        headerPanel.add(textContainer, BorderLayout.CENTER);

        // 🔹 MAIN CONTENT PANEL
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(bgColor);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridy = 0;
        contentPanel.add(createMenuButton("📘 Tutorials", e -> {
            dispose();
            new com.virtuallab.tutorial.TutorialPage().setVisible(true);
        }), gbc);

        gbc.gridy = 1;
        contentPanel.add(createMenuButton("🛠 Perform Experiments", e -> {
            dispose();
            new LaboratoryGUI().setVisible(true);
        }), gbc);

        gbc.gridy = 2;
        contentPanel.add(createMenuButton("📜 Past Experiments", e -> {
            int userId = UserSession.getUserId(); // Fetch the logged-in user's ID
            if (userId == -1) {
                JOptionPane.showMessageDialog(this, "❌ No user logged in!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dispose();
            new PastExperimentsPage().setVisible(true); // Pass userId to PastExperimentsPage
        }), gbc);


        gbc.gridy = 3;
        contentPanel.add(createMenuButton("🧠 Take a Quiz", e -> {
            dispose();
            new com.virtuallab.quiz.QuizPage().setVisible(true);
        }), gbc);

        gbc.gridy = 4;
        contentPanel.add(createMenuButton("📊 View Past Results", e -> {
            int userId = UserSession.getUserId();
            if (userId == -1) {
                JOptionPane.showMessageDialog(this, "❌ No user logged in!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            dispose();
            new ViewPastResults().setVisible(true);
        }), gbc);

        gbc.gridy = 5;
        contentPanel.add(createMenuButton("🚪 Logout", e -> {
            UserSession.clearSession();
            dispose();
            new com.virtuallab.auth.LoginPage();
        }), gbc);

        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
    }

    private JButton createMenuButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(300, 60));
        button.addActionListener(action);

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
