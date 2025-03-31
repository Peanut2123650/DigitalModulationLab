package com.virtuallab.database;

import java.sql.*;

public class UserDAO {

    // Validate user and return user ID if credentials are correct
    public int getUserIdIfValid(String username, String hashedPassword) {
        String query = "SELECT id FROM users WHERE username=? AND password=?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("id"); // Return user ID if login is successful
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if login fails
    }

    // Add user to the database with hashed password
    public boolean addUser(String username, String hashedPassword) {
        String checkQuery = "SELECT id FROM users WHERE username=?";
        String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {

            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                return false; // User already exists
            }

            insertStmt.setString(1, username);
            insertStmt.setString(2, hashedPassword);
            insertStmt.executeUpdate();
            return true; // Registration successful
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
