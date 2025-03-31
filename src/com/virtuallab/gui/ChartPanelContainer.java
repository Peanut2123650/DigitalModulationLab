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
        setLayout(new GridLayout(4, 1)); // Arrange charts in 4x1 grid
        modulatedChart = new ChartPanel(null); // Will be set later
        transmittedChart = new ChartPanel(null); // Will be set later
        demodulatedChart = new ChartPanel(null); // Will be set later
        carrierChart = new ChartPanel(null); // Will be set later

        // Add the chart panels to the container
        add(transmittedChart);
        add(carrierChart);
        add(modulatedChart);
        add(demodulatedChart);
    }

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
