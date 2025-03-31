package com.virtuallab;

import com.virtuallab.auth.LoginPage;
import com.virtuallab.database.DatabaseManager;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Ensure GUI runs on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize database connection
                DatabaseManager.getConnection();

                // Start the login process
                new LoginPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
