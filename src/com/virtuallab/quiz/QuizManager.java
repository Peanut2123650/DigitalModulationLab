package com.virtuallab.quiz;

import java.util.List;

public class QuizManager {
    private List<Integer> selectedQuestions;
    private int currentQuestionIndex;
    private int score;

    public QuizManager() {
        selectedQuestions = QuizData.getRandomQuestions(10);
        currentQuestionIndex = 0;
        score = 0;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < selectedQuestions.size();     // Ensures it stops at 10
    }

    public String getCurrentQuestion() {
        if (!hasMoreQuestions()) return "";
        return QuizData.getQuestion(selectedQuestions.get(currentQuestionIndex));
    }

    public String[] getCurrentOptions() {
        if (!hasMoreQuestions()) return new String[]{};
        return QuizData.getOptions(selectedQuestions.get(currentQuestionIndex));
    }

    public boolean checkAnswer(int selectedOption) {
        if (!hasMoreQuestions()) return false;

        int questionIndex = selectedQuestions.get(currentQuestionIndex);
        boolean isCorrect = (selectedOption == QuizData.getCorrectAnswer(questionIndex));

        if (isCorrect) {
            score++;
        }

        currentQuestionIndex++; // Move to next question
        return isCorrect;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1; // 1-based index for UI
    }

    public int getTotalQuestions() {
        return selectedQuestions.size();
    }

    public String getResultMessage() {
        double percentage = (double) score / getTotalQuestions();
        if (percentage >= 0.8) return "Excellent work! You really know your modulation techniques!";
        if (percentage >= 0.6) return "Good job! You have a solid understanding.";
        if (percentage >= 0.4) return "Not bad! Review the tutorials to improve.";
        return "Keep practicing! Try the tutorials first next time.";
    }
}
