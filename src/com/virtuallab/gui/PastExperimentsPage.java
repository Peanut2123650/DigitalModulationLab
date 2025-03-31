package com.virtuallab.gui;

import com.virtuallab.auth.UserSession;
import com.virtuallab.database.Experiment;
import com.virtuallab.database.ExperimentDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PastExperimentsPage extends JFrame {
    private final Color bgColor = new Color(21, 43, 89);       // Deep Navy Blue
    private final Color cardColor = new Color(235, 245, 255);  // Soft Azure Blue for readability
    private final Color textColor = Color.BLACK;               // Black text
    private final Color buttonColor = new Color(70, 130, 180); // Button blue
    private final Color hoverColor = new Color(100, 149, 237); // Hover color

    private JList<String> experimentList;
    private DefaultListModel<String> listModel;
    private JTextArea experimentDetails;

    public PastExperimentsPage() {
        int userId = UserSession.getUserId();
        System.out.println("User ID in PastExperimentsPage: " + userId); // Debug log

        // Check if user is logged in
        if (userId == -1) {
            JOptionPane.showMessageDialog(this, "❌ No user logged in!", "Error", JOptionPane.ERROR_MESSAGE);
            dispose(); // Close the window if no user is logged in
            return;
        }

        setTitle("Past Experiments");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(bgColor);
        setLocationRelativeTo(null);

        // 🔷 Header
        JLabel titleLabel = new JLabel("Past Experiments", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(15, 0, 15, 0));

        // 🔹 Experiment List Panel
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBackground(bgColor);
        listPanel.setBorder(new EmptyBorder(10, 15, 10, 15));

        listModel = new DefaultListModel<>();
        experimentList = new JList<>(listModel);
        experimentList.setFont(new Font("Arial", Font.PLAIN, 14));
        experimentList.setBackground(Color.WHITE);
        experimentList.setForeground(Color.BLACK);
        experimentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        experimentList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        experimentList.setFixedCellHeight(30); // Consistent spacing

        JScrollPane scrollPane = new JScrollPane(experimentList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Experiments"));
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // 🔹 Experiment Details Panel
        experimentDetails = new JTextArea();
        experimentDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        experimentDetails.setEditable(false);
        experimentDetails.setBackground(cardColor);
        experimentDetails.setForeground(textColor);
        experimentDetails.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        experimentDetails.setLineWrap(true);
        experimentDetails.setWrapStyleWord(true);

        JScrollPane detailsScrollPane = new JScrollPane(experimentDetails);
        detailsScrollPane.setBorder(BorderFactory.createTitledBorder("Experiment Details"));
        detailsScrollPane.setPreferredSize(new Dimension(400, 250));

        // 🔹 Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(bgColor);

        JButton viewDetailsButton = new JButton("View Details");
        JButton backButton = new JButton("Back to Main Menu");

        styleButton(viewDetailsButton);
        styleButton(backButton);

        viewDetailsButton.addActionListener(e -> {
            String selectedExperiment = experimentList.getSelectedValue();
            if (selectedExperiment != null) {
                displayExperimentDetails(selectedExperiment);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an experiment!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new MainMenu().setVisible(true);
        });

        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(backButton);

        // 🔹 Load Experiments from DB
        loadExperiments(userId);

        // Layout Setup
        add(titleLabel, BorderLayout.NORTH);
        add(listPanel, BorderLayout.WEST);
        add(detailsScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // ✅ Load Experiments into List, now filters by userId
    private void loadExperiments(int userId) {
        try {
            ExperimentDAO dao = new ExperimentDAO();
            List<Experiment> experiments = dao.getExperimentsByUserId(userId); // Fetch all experiments for the user
            listModel.clear();
            for (Experiment exp : experiments) {
                listModel.addElement(exp.getId() + " - " + exp.getExperimentName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading experiments!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ✅ Display Details in Text Area with Better Formatting
    private void displayExperimentDetails(String selectedExperiment) {
        try {
            int experimentId = Integer.parseInt(selectedExperiment.split(" - ")[0]);
            ExperimentDAO dao = new ExperimentDAO();
            Experiment experiment = dao.getExperimentById(experimentId);  // Corrected method call

            if (experiment != null) {
                experimentDetails.setText(
                        "Experiment ID: " + experiment.getId() + "\n\n" +
                                "Experiment Name: " + experiment.getExperimentName() + "\n\n" +
                                "Input Data: " + experiment.getInputText() + "\n\n" +
                                "Carrier Frequency: " + experiment.getCarrierFrequency() + " Hz\n\n" +
                                "Amplitude: " + experiment.getAmplitude() + " V\n\n" +
                                "Sampling Frequency: " + experiment.getSamplingFrequency() + " Hz\n\n" +
                                "Bit Duration: " + experiment.getBitDuration() + " s\n\n" +
                                "Modulation Type: " + experiment.getModulationType() + "\n\n" +
                                "Demodulated Output: " + experiment.getDemodulatedText()
                );
            } else {
                experimentDetails.setText("Experiment details not found.");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching experiment details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ✅ Button Styling
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hoverColor);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(buttonColor);
            }
        });
    }

    // ✅ Main Method (optional)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PastExperimentsPage().setVisible(true));  // Fetches userId from session
    }
}
