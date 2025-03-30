package com.virtuallab.tutorial;

import javax.swing.*;
import java.awt.*;

public class TutorialContentPanel extends JPanel {
    public TutorialContentPanel() {
        setLayout(new BorderLayout());
        setBackground(UIUtils.BG_COLOR);
        setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        JEditorPane tutorialPane = new JEditorPane();
        tutorialPane.setContentType("text/html");
        tutorialPane.setEditable(false);
        tutorialPane.setText(TutorialContent.getContent());
        tutorialPane.setBackground(UIUtils.BG_COLOR);
        tutorialPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tutorialPane.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(tutorialPane);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 210)));
        scrollPane.getViewport().setBackground(UIUtils.BG_COLOR);

        add(scrollPane, BorderLayout.CENTER);
    }
}
