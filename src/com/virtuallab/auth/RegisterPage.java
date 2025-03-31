package com.virtuallab.auth;

import javax.swing.*;
import java.awt.*;

public class RegisterPage {
    private JFrame frame;
    private JTextField userField;
    private JPasswordField passField;
    private JButton registerButton, backButton;
    private AuthService authService;

    // Color Palette
    private final Color deepNavy = new Color(21, 43, 89);
    private final Color softAzure = new Color(160, 195, 217);
    private final Color pureWhite = new Color(255, 255, 255);
    private final Color lightGray = new Color(217, 217, 217);
    private final Color charcoalGray = new Color(80, 91, 106);

    public RegisterPage() {
        authService = new AuthService();

        frame = new JFrame("Digital Modulation Lab - Register");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 500);
        panel.setBackground(deepNavy);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Register", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(pureWhite);

        // Username Field
        userField = new JTextField();
        userField.setBounds(50, 80, 300, 40);
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        userField.setBackground(softAzure);
        userField.setForeground(Color.BLACK);
        userField.setBorder(BorderFactory.createLineBorder(charcoalGray));

        // Password Field
        passField = new JPasswordField();
        passField.setBounds(50, 140, 300, 40);
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setBackground(softAzure);
        passField.setForeground(Color.BLACK);
        passField.setBorder(BorderFactory.createLineBorder(charcoalGray));

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(50, 200, 130, 40);
        registerButton.setBackground(lightGray);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBorder(BorderFactory.createLineBorder(charcoalGray));
        registerButton.addActionListener(e -> registerUser());

        // Back Button
        backButton = new JButton("Back to Login");
        backButton.setBounds(220, 200, 130, 40);
        backButton.setBackground(lightGray);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBorder(BorderFactory.createLineBorder(charcoalGray));
        backButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(LoginPage::new);
        });

        panel.add(titleLabel);
        panel.add(userField);
        panel.add(passField);
        panel.add(registerButton);
        panel.add(backButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registerUser() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (authService.register(username, password)) {
            JOptionPane.showMessageDialog(frame, "Registration Successful!");
            frame.dispose();
            SwingUtilities.invokeLater(LoginPage::new);
        } else {
            JOptionPane.showMessageDialog(frame, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
