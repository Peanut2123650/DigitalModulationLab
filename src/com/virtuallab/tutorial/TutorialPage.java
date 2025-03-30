package com.virtuallab.tutorial;

import javax.swing.*;
import java.awt.*;

public class TutorialPage extends JFrame {
    public TutorialPage() {
        setTitle("Modulation Techniques Tutorial");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(UIUtils.BG_COLOR);

        // Add panels to frame
        add(new TutorialHeaderPanel(this), BorderLayout.NORTH);
        add(new TutorialContentPanel(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TutorialPage().setVisible(true));
    }
}
