package com.virtuallab.gui;

import javax.swing.*;
import java.awt.*;

public class LaboratoryGUI extends JFrame {
    public LaboratoryGUI() {
        setTitle("Digital Modulation Lab");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);  // Ensure the window is large enough to avoid squeezing
        setLayout(new BorderLayout());

        // Create panels
        InputPanel inputPanel = new InputPanel();
        ButtonsPanel buttonsPanel = new ButtonsPanel();
        ChartPanelContainer chartPanel = new ChartPanelContainer();
        JLabel resultLabel = new JLabel(""); // Add missing resultLabel

        // Wrap chart panel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(chartPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  // Enable vertical scrolling

        // Add components to frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);  // Add scrollable chart panel in center
        add(buttonsPanel, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.WEST); // Optional placement

        // Attach event handlers
        new EventHandlers(
                inputPanel.textInput,       // bitstream (digital data)
                inputPanel.frequencyInput,  // carrier frequency
                inputPanel.fsInput,         // sampling frequency
                inputPanel.tbInput,         // bit duration
                inputPanel.amplitudeInput,
                inputPanel.modulationType,
                buttonsPanel.resultButton,
                buttonsPanel.resetButton,
                buttonsPanel.demodulateButton,
                chartPanel.getModulatedChart(),    // modulated chart
                chartPanel.getTransmittedChart(), // transmitted chart (digital data waveform)
                chartPanel.getDemodulatedChart(), // demodulated chart
                chartPanel.getCarrierChart(),     // carrier signal chart
                resultLabel,
                buttonsPanel.backButton,
                this
        );

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LaboratoryGUI::new);
    }
}
