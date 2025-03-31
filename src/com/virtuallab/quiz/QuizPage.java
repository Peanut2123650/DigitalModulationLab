package com.virtuallab.quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.virtuallab.gui.MainMenu; // Import MainMenu to navigate back

public class QuizPage extends JFrame {
    private QuizManager quizManager;
    private JLabel questionLabel, progressLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup optionGroup;
    private JButton nextButton, backButton;

    private final int userId = 1;

    private final Color bgColor = new Color(21, 43, 89);
    private final Color cardColor = new Color(50, 60, 80);
    private final Color textColor = Color.WHITE;
    private final Color buttonColor = new Color(70, 130, 180);
    private final Color hoverColor = new Color(100, 149, 237);
    private final Color dangerColor = new Color(200, 50, 50);

    public QuizPage() {
        quizManager = new QuizManager();
        setTitle("Quiz - Digital Modulation Lab");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Digital Modulation Quiz", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(textColor);
        headerPanel.setBackground(buttonColor);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        mainPanel.setBackground(bgColor);

        questionLabel = new JLabel("Question", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setForeground(textColor);
        questionLabel.setPreferredSize(new Dimension(getWidth(), 60));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        optionsPanel.setBackground(bgColor);
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionButtons[i].setForeground(textColor);
            optionButtons[i].setBackground(cardColor);
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));
        bottomPanel.setBackground(bgColor);

        progressLabel = new JLabel("Question 1 of 10", SwingConstants.CENTER);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        progressLabel.setForeground(textColor);
        bottomPanel.add(progressLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(bgColor);

        backButton = new JButton("⬅ Back to Main Menu");
        styleButton(backButton, dangerColor);
        backButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    this, "Are you sure you want to quit the quiz?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE
            );
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new MainMenu().setVisible(true);
            }
        });

        nextButton = new JButton("Next ➡");
        styleButton(nextButton, buttonColor);
        nextButton.addActionListener(new NextButtonListener());

        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        loadNextQuestion();
        setVisible(true);
    }

    private void loadNextQuestion() {
        if (quizManager.hasMoreQuestions()) {
            questionLabel.setText(quizManager.getCurrentQuestion());
            String[] options = quizManager.getCurrentOptions();

            optionGroup.clearSelection();
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options[i]);
            }

            progressLabel.setText("Question " + quizManager.getCurrentQuestionNumber() + " of " + quizManager.getTotalQuestions());
        } else {
            showResult();
        }
    }

    private void showResult() {
        quizManager.saveQuizResult();   // Save quiz result in the database

        JOptionPane.showMessageDialog(this,
                "Quiz Completed!\nYour Score: " + quizManager.getScore() + "/" + quizManager.getTotalQuestions() +
                        "\n" + quizManager.getResultMessage(),
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new MainMenu().setVisible(true);
    }

    private class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedOption = -1;
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    selectedOption = i;
                    break;
                }
            }
            if (selectedOption == -1) {
                JOptionPane.showMessageDialog(null, "Please select an answer!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            quizManager.checkAnswer(selectedOption);
            loadNextQuestion();
        }
    }

    private void styleButton(JButton button, Color color) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizPage::new);
    }
}
