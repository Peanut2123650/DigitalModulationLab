package com.virtuallab.gui;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

public class ChartPanelContainer extends JPanel {
    private ChartPanel modulatedGraph, transmittedGraph, demodulatedGraph;

    public ChartPanelContainer() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Waveform Display"));

        // Initialize empty charts
        modulatedGraph = new ChartPanel(null);
        transmittedGraph = new ChartPanel(null);
        demodulatedGraph = new ChartPanel(null);

        add(modulatedGraph);
        add(transmittedGraph);
        add(demodulatedGraph);
    }

    // Setters for ...
    public void setModulatedChart(JFreeChart chart) {
        modulatedGraph.setChart(chart);
    }

    public void setTransmittedChart(JFreeChart chart) {
        transmittedGraph.setChart(chart);
    }

    public void setDemodulatedChart(JFreeChart chart) {
        demodulatedGraph.setChart(chart);
    }

    // Getters for EventHandlers class
    public ChartPanel getModulatedChart() {
        return modulatedGraph;
    }

    public ChartPanel getTransmittedChart() {
        return transmittedGraph;
    }

    public ChartPanel getDemodulatedChart() {
        return demodulatedGraph;
    }
}
