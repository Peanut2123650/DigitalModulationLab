package com.virtuallab.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExperimentDAO {
    private Connection conn;

    public ExperimentDAO() throws SQLException {
        this.conn = DatabaseManager.getConnection();
    }

    // ✅ Insert experiment record
    public int insertExperiment(String experimentName, String inputText, double carrierFrequency,
                                double amplitude, int samplingFrequency, double bitDuration,
                                String modulationType, String demodulatedText) {

        String query = "INSERT INTO experiments (user_id, experiment_name, input_text, carrier_frequency, amplitude, " +
                "sampling_frequency, bit_duration, modulation_type, demodulated_text) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, 1);  // Placeholder user_id
            stmt.setString(2, experimentName);
            stmt.setString(3, inputText);
            stmt.setDouble(4, carrierFrequency);
            stmt.setDouble(5, amplitude);
            stmt.setInt(6, samplingFrequency);
            stmt.setDouble(7, bitDuration);
            stmt.setString(8, modulationType);
            stmt.setString(9, demodulatedText);

            stmt.executeUpdate();
            System.out.println("Experiment saved successfully!");

            // Retrieve the generated experiment ID
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void updateDemodulatedText(int experimentId, String demodulatedText) {
        String sql = "UPDATE experiments SET demodulated_text = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, demodulatedText);
            stmt.setInt(2, experimentId);

            stmt.executeUpdate();
            System.out.println("Demodulated text updated for experiment ID: " + experimentId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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



    // ✅ Fetch all experiments
    public List<String> getAllExperiments() {
        List<String> experiments = new ArrayList<>();
        String query = "SELECT experiment_name FROM experiments";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                experiments.add(rs.getString("experiment_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return experiments;
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
