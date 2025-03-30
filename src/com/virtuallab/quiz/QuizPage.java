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

    public QuizPage() {
        quizManager = new QuizManager();
        setTitle("Quiz - Digital Modulation Lab");
        setSize(800, 500); // Increased window size for better spacing
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Digital Modulation Quiz", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.setBackground(new Color(70, 130, 180));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Question Display
        questionLabel = new JLabel("Question", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setPreferredSize(new Dimension(getWidth(), 60));
        mainPanel.add(questionLabel, BorderLayout.NORTH);

        // Options Panel
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // More spacing
        optionButtons = new JRadioButton[4];
        optionGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            optionGroup.add(optionButtons[i]);
            optionsPanel.add(optionButtons[i]);
        }

        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        // Bottom Panel (Navigation)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

        // Progress Label
        progressLabel = new JLabel("Question 1 of 10", SwingConstants.CENTER);
        progressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(progressLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Back Button
        backButton = new JButton("Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            dispose(); // Close the quiz window
            new MainMenu(); // Open the main menu
        });

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(new Color(70, 130, 180));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
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
            for (int i = 0; i < optionButtons.length; i++) {
                optionButtons[i].setText(options[i]);
                optionButtons[i].setSelected(false);
            }
            progressLabel.setText("Question " + quizManager.getCurrentQuestionNumber() + " of " + quizManager.getTotalQuestions());
        } else {
            showResult();
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this,
                "Quiz Completed!\nYour Score: " + quizManager.getScore() + "/" + quizManager.getTotalQuestions() +
                        "\n" + quizManager.getResultMessage(),
                "Quiz Result", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new MainMenu(); // Return to Main Menu after quiz completion
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizPage::new);
    }
}
