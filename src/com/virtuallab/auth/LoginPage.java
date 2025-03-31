package com.virtuallab.auth;

import javax.swing.*;
import java.awt.*;
import com.virtuallab.gui.MainMenu;

public class LoginPage {
    private JFrame frame;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton, registerButton;
    private AuthService authService;

    // Color Palette
    private final Color deepNavy = new Color(21, 43, 89);
    private final Color softAzure = new Color(160, 195, 217);
    private final Color pureWhite = new Color(255, 255, 255);
    private final Color lightGray = new Color(217, 217, 217);
    private final Color charcoalGray = new Color(80, 91, 106);

    public LoginPage() {
        authService = new AuthService();

        frame = new JFrame("Digital Modulation Lab - Login");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 400, 500);
        panel.setBackground(deepNavy);
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Digital Modulation Lab", SwingConstants.CENTER);
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

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(50, 200, 130, 40);
        loginButton.setBackground(lightGray);
        loginButton.setForeground(Color.BLACK);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBorder(BorderFactory.createLineBorder(charcoalGray));
        loginButton.addActionListener(e -> authenticateUser());

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setBounds(220, 200, 130, 40);
        registerButton.setBackground(lightGray);
        registerButton.setForeground(Color.BLACK);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBorder(BorderFactory.createLineBorder(charcoalGray));
        registerButton.addActionListener(e -> {
            frame.dispose();
            SwingUtilities.invokeLater(RegisterPage::new);
        });

        panel.add(titleLabel);
        panel.add(userField);
        panel.add(passField);
        panel.add(loginButton);
        panel.add(registerButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void authenticateUser() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        int userId = authService.login(username, password);
        if (userId != -1) {
            JOptionPane.showMessageDialog(frame, "Login Successful!");

            // Store user ID in session
            UserSession.setUserId(userId);
            System.out.println("User ID after login: " + userId); // Debug log

            frame.dispose();
            SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true));
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
