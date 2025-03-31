package com.virtuallab.quiz;

import com.virtuallab.auth.UserSession;
import com.virtuallab.database.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizManager {
    private List<Integer> selectedQuestions;
    private int currentQuestionIndex;
    private int score;
    private final int totalQuestions = 5; // Fixed number of quiz questions

    public QuizManager() {
        selectedQuestions = QuizData.getRandomQuestions(totalQuestions);
        currentQuestionIndex = 0;
        score = 0;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < selectedQuestions.size();
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

        currentQuestionIndex++;
        return isCorrect;
    }

    public int getScore() {
        return score;
    }

    public int getCurrentQuestionNumber() {
        return currentQuestionIndex + 1; // 1-based index for UI
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getResultMessage() {
        double percentage = (double) score / totalQuestions;
        if (percentage >= 0.8) return "Excellent!";
        if (percentage >= 0.6) return "Good job!";
        if (percentage >= 0.4) return "Needs improvement.";
        return "Keep practicing!";
    }

    public void saveQuizResult() {
        int userId = UserSession.getUserId(); // Get logged-in user
        if (userId == -1) {
            System.err.println("❌ No user logged in. Cannot save quiz results.");
            return;
        }

        String query = "INSERT INTO quiz_results (user_id, score, total_questions, result_message) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, score);
            stmt.setInt(3, getTotalQuestions());
            stmt.setString(4, getResultMessage());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Error saving quiz result: " + e.getMessage());
        }
    }


    // Retrieves past quiz results for a user
    public static List<String> retrievePastResults(int userId) {
        List<String> results = new ArrayList<>();
        String query = "SELECT quiz_date, score FROM quiz_results WHERE user_id = ? ORDER BY quiz_date DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String date = rs.getString("quiz_date");
                int score = rs.getInt("score");
                results.add(date + " - Score: " + score);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Error retrieving past results: " + e.getMessage());
        }

        return results;
    }
}
