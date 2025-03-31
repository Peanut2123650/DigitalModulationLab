package com.virtuallab.gui;

import com.virtuallab.auth.UserSession;
import com.virtuallab.database.DatabaseManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewPastResults extends JFrame {
    private JTable resultsTable;
    private JButton backButton;

    public ViewPastResults() {
        int userId = UserSession.getUserId();

        if (userId == -1) {
            JOptionPane.showMessageDialog(this, "❌ No user logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        setTitle("Past Quiz Results");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnNames = {"Score", "Total Questions", "Result"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        resultsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        resultsTable.setRowHeight(25);
        loadQuizResults(tableModel, userId);

        JScrollPane scrollPane = new JScrollPane(resultsTable);
        add(scrollPane, BorderLayout.CENTER);

        backButton = new JButton("⬅ Back to Main Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true);
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadQuizResults(DefaultTableModel tableModel, int userId) {
        String query = "SELECT score, total_questions, result_message FROM quiz_results WHERE user_id = ? ORDER BY id DESC";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            boolean hasResults = false;
            while (rs.next()) {
                hasResults = true;
                int score = rs.getInt("score");
                int totalQuestions = rs.getInt("total_questions");
                String resultMessage = rs.getString("result_message");
                tableModel.addRow(new Object[]{score, totalQuestions, resultMessage});
            }

            if (!hasResults) {
                JOptionPane.showMessageDialog(this, "📂 No past results found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "❌ Error fetching results!", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ViewPastResults::new);
    }
}
