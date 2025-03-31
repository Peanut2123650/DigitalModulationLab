package com.virtuallab.gui;

import org.jfree.chart.ChartPanel;
import javax.swing.*;
import java.awt.*;

public class ChartPanelContainer extends JPanel {
    private ChartPanel modulatedChart;
    private ChartPanel transmittedChart;
    private ChartPanel demodulatedChart;
    private ChartPanel carrierChart;

    public ChartPanelContainer() {
        // Set a simple layout like FlowLayout to let components take their natural size
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));  // Center-aligned, with spacing

        // Initialize ChartPanels (with null, will be set later)
        modulatedChart = new ChartPanel(null);
        transmittedChart = new ChartPanel(null);
        demodulatedChart = new ChartPanel(null);
        carrierChart = new ChartPanel(null);

        // Set preferred size for each chart panel to ensure they don't shrink
        int graphWidth = 800;  // Width of each graph
        int graphHeight = 300; // Height of each graph

        modulatedChart.setPreferredSize(new Dimension(graphWidth, graphHeight));
        transmittedChart.setPreferredSize(new Dimension(graphWidth, graphHeight));
        demodulatedChart.setPreferredSize(new Dimension(graphWidth, graphHeight));
        carrierChart.setPreferredSize(new Dimension(graphWidth, graphHeight));

        // Add chart panels to container
        add(transmittedChart);
        add(carrierChart);
        add(modulatedChart);
        add(demodulatedChart);
    }

    // Getter methods for accessing the chart panels
    public ChartPanel getModulatedChart() {
        return modulatedChart;
    }

    public ChartPanel getTransmittedChart() {
        return transmittedChart;
    }

    public ChartPanel getDemodulatedChart() {
        return demodulatedChart;
    }

    public ChartPanel getCarrierChart() {
        return carrierChart;
    }
}
