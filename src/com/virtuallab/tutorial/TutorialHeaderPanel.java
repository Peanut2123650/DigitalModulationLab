package com.virtuallab.tutorial;

import javax.swing.*;
import java.awt.*;
import com.virtuallab.gui.MainMenu;

public class TutorialHeaderPanel extends JPanel {
    public TutorialHeaderPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(UIUtils.PRIMARY_COLOR);
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton backButton = UIUtils.createStyledButton("← Back to Main Menu", 14, Color.WHITE, UIUtils.PRIMARY_COLOR);
        backButton.addActionListener(e -> {
            parentFrame.dispose();
            new MainMenu().setVisible(true);
        });

        JLabel titleLabel = new JLabel("📖 Modulation Techniques Tutorial", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        add(backButton, BorderLayout.WEST);
        add(titleLabel, BorderLayout.CENTER);
    }
}
