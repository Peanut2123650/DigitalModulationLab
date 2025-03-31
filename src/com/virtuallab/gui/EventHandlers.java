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
    private ChartPanel modulatedGraph, transmittedGraph, demodulatedGraph, carrierGraph;
    private JLabel resultLabel;
    private int latestExperimentId = -1; // Store the latest experiment ID

    public EventHandlers(JTextField textInput, JTextField wInput, JTextField fsInput, JTextField tbInput,
                         JTextField amplitudeInput, JComboBox<String> modulationType,
                         JButton resultButton, JButton resetButton, JButton demodulationButton,
                         ChartPanel modulatedGraph, ChartPanel transmittedGraph, ChartPanel demodulatedGraph,
                         ChartPanel carrierGraph, JLabel resultLabel, JButton backButton, JFrame labFrame) {

        //For back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labFrame.dispose(); // Close the lab window
                SwingUtilities.invokeLater(() -> new MainMenu().setVisible(true)); // Open com.virtuallab.Main Menu
            }
        });


        // Initialize the input fields and buttons
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
        this.carrierGraph = carrierGraph;  // Store the carrier graph
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
            String text = textInput.getText();
            double w = Double.parseDouble(wInput.getText()); // Carrier frequency
            int fs = Integer.parseInt(fsInput.getText());   // Sampling frequency
            double amplitude = Double.parseDouble(amplitudeInput.getText());
            double tb = Double.parseDouble(tbInput.getText()); // Bit duration
            String modType = (String) modulationType.getSelectedItem();

            // Validate input values
            if (w < 50 || w > 100) {
                JOptionPane.showMessageDialog(null, "Error: Carrier frequency must be between 50 and 100 Hz.");
                return;
            }

            if (amplitude < 1 || amplitude > 5) {
                JOptionPane.showMessageDialog(null, "Error: Amplitude must be between 1 and 5.");
                return;
            }

            // Convert text to binary bitstream
            String bitstream = SignalProcessor.asciiToBinary(text);

            // Generate the digital data waveform (for transmitted graph)
            double[] digitalDataWaveform = SignalProcessor.generateDigitalDataWaveform(bitstream, fs, tb);

            // Generate the carrier signal waveform
            double[] carrierWaveform = SignalProcessor.generateCarrierWaveform(w, amplitude, fs, tb, bitstream.length());

            // Perform modulation
            double[] modulatedSignal = ModulationProcessor.modulate(bitstream, modType, w, tb, fs);

            if (modulatedSignal == null || modulatedSignal.length == 0) {
                System.out.println("ERROR: Modulated signal is empty!");
                return;
            }

            // ✅ Use createBitChart() for transmitted digital data
            transmittedGraph.setChart(GraphUtils.createBitChart("Digital Data (Transmitted)", bitstream));

            // ✅ Use createChart() for carrier and modulated signals
            carrierGraph.setChart(GraphUtils.createChart("Carrier Signal", carrierWaveform));
            modulatedGraph.setChart(GraphUtils.createChart("Modulated Signal", modulatedSignal));

            // Refresh the graphs
            transmittedGraph.revalidate();
            transmittedGraph.repaint();
            carrierGraph.revalidate();
            carrierGraph.repaint();
            modulatedGraph.revalidate();
            modulatedGraph.repaint();

            // Store experiment in the database
            ExperimentDAO experimentDAO = new ExperimentDAO();
            int experimentId = experimentDAO.insertExperiment(
                    "Digital Modulation Experiment", text, w, amplitude, fs, tb, modType
            );

            if (experimentId == -1) {
                System.out.println("ERROR: Experiment insertion failed!");
            } else {
                latestExperimentId = experimentId;
                System.out.println("Experiment inserted successfully with ID: " + experimentId);
            }

            // ✅ Show the bitstream in resultLabel, not the signal
            resultLabel.setText("Transmitted Bitstream: " + bitstream);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error: Please enter valid numeric values.");
            ex.printStackTrace();
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
            if (demodulatedBits == null || demodulatedBits.isEmpty()) {
                System.out.println("ERROR: Demodulated bits are empty!");
                return;
            }

            System.out.println("Demodulated Bits: " + demodulatedBits);

            // Convert binary bits to text
            String decodedText = SignalProcessor.binaryToAscii(demodulatedBits);
            if (decodedText == null || decodedText.isEmpty()) {
                System.out.println("ERROR: Decoded text is empty!");
                return;
            }

            System.out.println("Decoded Text: " + decodedText);

            // Display demodulated data
            demodulatedGraph.setChart(GraphUtils.createBitChart("Demodulated Bits", demodulatedBits));
            demodulatedGraph.revalidate();
            demodulatedGraph.repaint();

            // Update the result label with decoded text
            resultLabel.setText("Decoded Text: " + decodedText);

            // Ensure a valid experiment ID was stored
            if (latestExperimentId == -1) {
                System.out.println("ERROR: No experiment found to update!");
                return;
            }

            // Update the database with the decoded text
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
