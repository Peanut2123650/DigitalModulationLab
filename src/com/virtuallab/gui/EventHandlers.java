package com.virtuallab.gui;

import com.virtuallab.database.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

import org.jfree.chart.ChartPanel;
import com.virtuallab.modulation.ModulationProcessor;
import com.virtuallab.utils.*;

public class EventHandlers {
    private JTextField textInput, wInput, fsInput, tbInput, amplitudeInput;
    private JComboBox<String> modulationType;
    private JButton resultButton, resetButton, demodulationButton;
    private ChartPanel modulatedGraph, transmittedGraph, demodulatedGraph;
    private JLabel resultLabel;
    private int latestExperimentId = -1; // Store the latest experiment ID

    public EventHandlers(JTextField textInput, JTextField wInput, JTextField fsInput, JTextField tbInput,
                         JTextField amplitudeInput, JComboBox<String> modulationType,
                         JButton resultButton, JButton resetButton, JButton demodulationButton,
                         ChartPanel modulatedGraph, ChartPanel transmittedGraph, ChartPanel demodulatedGraph,
                         JLabel resultLabel, JButton backButton, JFrame labFrame) {

        //For back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labFrame.dispose(); // Close the lab window
                SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true)); // Open Main Menu
            }
        });
        this.textInput = textInput;
        this.wInput = wInput; // carrier frequency
        this.fsInput = fsInput; // sampling frequency
        this.tbInput = tbInput; // bit duration
        this.amplitudeInput = amplitudeInput; // peak-to-peak amplitude
        this.modulationType = modulationType;
        this.resultButton = resultButton;
        this.resetButton = resetButton;
        this.demodulationButton = demodulationButton;
        this.modulatedGraph = modulatedGraph;
        this.transmittedGraph = transmittedGraph;
        this.demodulatedGraph = demodulatedGraph;
        this.resultLabel = resultLabel;

        attachListeners();
    }

    private void attachListeners() {
        resultButton.addActionListener(e -> performModulation());
        resetButton.addActionListener(e -> resetFields());
        demodulationButton.addActionListener(e -> performDemodulation());
    }

    private void performModulation() {
        try {
            // Get input values
            String text = textInput.getText();  //to get the input text
            double w = Double.parseDouble(wInput.getText());    // to get the carrier frequency
            int fs = Integer.parseInt(fsInput.getText());   // to get the sampling frequency
            double amplitude = Double.parseDouble(amplitudeInput.getText());  // Get amplitude from user input
            double tb = Double.parseDouble(tbInput.getText());  // to get the bit duration
            String modType = (String) modulationType.getSelectedItem(); // to get the modulation type

            // Validate input values
            if (w < 50 || w > 100) {
                JOptionPane.showMessageDialog(null, "Error: Carrier frequency must be between 50 and 100 Hz.");
                return;  // Exit the function if the frequency is out of range
            }

            if (amplitude < 1 || amplitude > 5) {
                JOptionPane.showMessageDialog(null, "Error: Amplitude must be between 1 and 5.");
                return;  // Exit the function if the amplitude is out of range
            }

            // Convert text to binary bitstream
            String bitstream = SignalProcessor.asciiToBinary(text);

            // Perform modulation (but do NOT store it)
            double[] modulatedSignal = ModulationProcessor.modulate(bitstream, modType, w, tb, fs);

            if (modulatedSignal == null || modulatedSignal.length == 0) {
                System.out.println("ERROR: Modulated signal is empty!");
                return;
            }

            // Display modulated signal on the graph
            modulatedGraph.setChart(GraphUtils.createChart("Modulated Signal", modulatedSignal));
            modulatedGraph.revalidate();
            modulatedGraph.repaint();

            // Store only parameters in the database
            ExperimentDAO experimentDAO = new ExperimentDAO();
            int experimentId = experimentDAO.insertExperiment(
                    //update so that amplitude's value is taken as input as well
                    "Digital Modulation Experiment", text, w, amplitude, fs, tb, modType, ""
            );

            if (experimentId == -1) {
                System.out.println("ERROR: Experiment insertion failed!");
            } else {
                latestExperimentId = experimentId;
                System.out.println("Experiment inserted successfully with ID: " + experimentId);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }


    private void performDemodulation() {
        try {
            // Get input values
            double carrierFreq = Double.parseDouble(wInput.getText());
            double tb = Double.parseDouble(tbInput.getText());
            int fs = Integer.parseInt(fsInput.getText());
            String modType = (String) modulationType.getSelectedItem();

            // Debugging prints
            System.out.println("Performing Demodulation with:");
            System.out.println("Carrier Frequency: " + carrierFreq);
            System.out.println("Bit Duration: " + tb);
            System.out.println("Sampling Frequency: " + fs);
            System.out.println("Modulation Type: " + modType);

            // Perform demodulation
            String demodulatedBits = ModulationProcessor.demodulate(modType, carrierFreq, tb, fs);
            System.out.println("Demodulated Bits: " + demodulatedBits);

            // Convert binary bits to text
            String decodedText = SignalProcessor.binaryToAscii(demodulatedBits);
            System.out.println("Decoded Text: " + decodedText);

            if (decodedText == null || decodedText.isEmpty()) {
                System.out.println("ERROR: Decoded text is empty!");
                return;
            }

            // Display demodulated data
            demodulatedGraph.setChart(GraphUtils.createBitChart("Demodulated Bits", demodulatedBits));
            resultLabel.setText("Decoded Text: " + decodedText);
            demodulatedGraph.revalidate();
            demodulatedGraph.repaint();

            // Ensure a valid experiment ID was stored
            if (latestExperimentId == -1) {
                System.out.println("ERROR: No experiment found to update!");
                return;
            }

            // ✅ Update the database with the decoded text
            System.out.println("Updating database with decoded text for experiment ID: " + latestExperimentId);
            ExperimentDAO.updateDemodulatedText(latestExperimentId, decodedText);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in demodulation: " + e.getMessage());
        }
    }

    private void resetFields() {
        textInput.setText("");
        wInput.setText("");
        fsInput.setText("");
        tbInput.setText("");
        amplitudeInput.setText("");
        modulationType.setSelectedIndex(0);
        resultLabel.setText("");
    }
}
