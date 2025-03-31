package com.virtuallab.gui;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    JTextField textInput, frequencyInput, amplitudeInput, tbInput, fsInput;
    JComboBox<String> modulationType;

    public InputPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(160, 195, 217)); // Soft Azure Blue
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 2),
                "Input Parameters", 0, 0, new Font("Arial", Font.BOLD, 14), Color.BLACK));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Add labeled input fields
        addInputField("Digital Data Stream:", textInput = createStyledTextField(), gbc, 0);
        addInputField("Carrier Frequency (50-100 Hz):", frequencyInput = createStyledTextField(), gbc, 1);
        addInputField("Sampling Frequency:", fsInput = createStyledTextField(), gbc, 2);
        addInputField("Peak-to-Peak Amplitude (1V-5V):", amplitudeInput = createStyledTextField(), gbc, 3);
        addInputField("Bit Duration (Tb):", tbInput = createStyledTextField(), gbc, 4);

        // Modulation type dropdown
        JLabel modTypeLabel = createStyledLabel("Modulation Type:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(modTypeLabel, gbc);

        modulationType = new JComboBox<>(new String[]{"FSK", "BPSK", "QPSK"});
        modulationType.setFont(new Font("Arial", Font.BOLD, 14));
        modulationType.setBackground(Color.WHITE);
        modulationType.setForeground(Color.BLACK);
        modulationType.setPreferredSize(new Dimension(150, 30));

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(modulationType, gbc);
    }

    private void addInputField(String labelText, JTextField textField, GridBagConstraints gbc, int row) {
        JLabel label = createStyledLabel(labelText);
        gbc.gridx = 0;
        gbc.gridy = row;
        add(label, gbc);

        gbc.gridx = 1;
        gbc.gridy = row;
        add(textField, gbc);
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(200, 30));
        textField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return textField;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        return label;
    }
}
