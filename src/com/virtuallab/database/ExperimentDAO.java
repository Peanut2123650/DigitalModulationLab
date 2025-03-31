package com.virtuallab.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperimentDAO {
    private Connection conn;

    // Database credentials (Move these constants to a centralized config file or class if needed)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/virtuallab"; // Example URL
    private static final String DB_USER = "root"; // Database username (change as needed)
    private static final String DB_PASSWORD = "Pr@12776"; // Database password (change as needed)

    public ExperimentDAO() throws SQLException {
        this.conn = DatabaseManager.getConnection(); // Assuming DatabaseManager handles connection pooling
    }

    // ✅ Insert experiment record
    public int insertExperiment(String experimentName, String inputText, double carrierFrequency, double amplitude, int samplingFrequency, double bitDuration, String modulationType, int userId) {
        String query = "INSERT INTO experiments (user_id, experiment_name, input_text, carrier_frequency, amplitude, sampling_frequency, bit_duration, modulation_type) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, userId);  // Set user_id
            statement.setString(2, experimentName);
            statement.setString(3, inputText);
            statement.setDouble(4, carrierFrequency);
            statement.setDouble(5, amplitude);
            statement.setInt(6, samplingFrequency);
            statement.setDouble(7, bitDuration);
            statement.setString(8, modulationType);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);  // Return the generated experiment ID
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;  // Return -1 if insertion failed
    }


    // ✅ Update demodulated text
    public static void updateDemodulatedText(int experimentId, String demodulatedText) {
        String sql = "UPDATE experiments SET demodulated_text = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, demodulatedText);
            stmt.setInt(2, experimentId);
            stmt.executeUpdate();

            System.out.println("✅ Demodulated text updated for Experiment ID: " + experimentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get last inserted experiment ID
    public int getLastInsertedExperimentId() {
        String query = "SELECT MAX(id) FROM experiments";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1); // Return latest experiment ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if no experiment exists
    }

    // ✅ Fetch all experiment names
    public List<Experiment> getAllExperiments() {
        List<Experiment> experiments = new ArrayList<>();
        String query = "SELECT * FROM experiments";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                experiments.add(new Experiment(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("experiment_name"),
                        rs.getString("input_text"),
                        rs.getDouble("carrier_frequency"),
                        rs.getDouble("amplitude"),
                        rs.getInt("sampling_frequency"),
                        rs.getDouble("bit_duration"),
                        rs.getString("modulation_type"),
                        rs.getString("demodulated_text")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experiments;
    }

    // ✅ Fetch experiments by user ID
    // ✅ Get experiments by user ID
    // ✅ Fetch experiments by user ID
    public List<Experiment> getExperimentsByUserId(int userId) throws SQLException {
        List<Experiment> experiments = new ArrayList<>();
        String query = "SELECT * FROM experiments WHERE user_id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                experiments.add(new Experiment(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("experiment_name"),
                        resultSet.getString("input_text"),
                        resultSet.getDouble("carrier_frequency"),
                        resultSet.getDouble("amplitude"),
                        resultSet.getInt("sampling_frequency"),
                        resultSet.getDouble("bit_duration"),
                        resultSet.getString("modulation_type"),
                        resultSet.getString("demodulated_text")
                ));
            }
        }

        return experiments; // Return the list of experiments
    }

    // Add this method to ExperimentDAO to fetch a single experiment by its ID.
    public Experiment getExperimentById(int experimentId) {
        Experiment experiment = null;
        String query = "SELECT * FROM experiments WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, experimentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    experiment = new Experiment(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getString("experiment_name"),
                            rs.getString("input_text"),
                            rs.getDouble("carrier_frequency"),
                            rs.getDouble("amplitude"),
                            rs.getInt("sampling_frequency"),
                            rs.getDouble("bit_duration"),
                            rs.getString("modulation_type"),
                            rs.getString("demodulated_text")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return experiment;
    }

    // ✅ Close the connection when done
    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
