package com.virtuallab.gui;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    JTextField textInput, frequencyInput, amplitudeInput, tbInput, fsInput;
    JComboBox<String> modulationType;

    public InputPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBorder(BorderFactory.createTitledBorder("Input Parameters"));

        // Text input
        textInput = new JTextField();
        add(new JLabel("Digital Data Stream:"));
        add(textInput);

        //Carrier Frequency input
        frequencyInput = new JTextField();
        add(new JLabel("Carrier Frequency (50-100 Hz):"));
        add(frequencyInput);

        //Sampling Frequency input
        fsInput = new JTextField();
        add(new JLabel("Sampling Frequency:"));
        add(fsInput);


        // Amplitude input
        amplitudeInput = new JTextField();
        add(new JLabel("Peak-to-Peak Amplitude (1V-5V):"));
        add(amplitudeInput);

        // Bit Duration input (Tb)
        tbInput = new JTextField();
        add(new JLabel("Bit Duration (Tb):"));
        add(tbInput);

        // Modulation type
        String[] modulations = {"FSK", "BPSK", "QPSK"};
        modulationType = new JComboBox<>(modulations);
        add(new JLabel("Modulation Type:"));
        add(modulationType);
    }
}
