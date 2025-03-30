package com.virtuallab.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class GraphUtils {

    public static JFreeChart createChart(String title, double[] signal) {
        XYSeries series = new XYSeries(title);
        for (int i = 0; i < signal.length; i++) {
            series.add(i, signal[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                title, "Time (s)", "Amplitude",
                dataset, PlotOrientation.VERTICAL, false, true, false
        );

        return chart;
    }

    public static JFreeChart createBitChart(String title, String bitstream) {
        XYSeries series = new XYSeries(title);
        for (int i = 0; i < bitstream.length(); i++) {
            double bitValue = bitstream.charAt(i) == '1' ? 1.0 : 0.0;
            series.add(i, bitValue);
        }

        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYStepChart(
                title, "Bits", "Amplitude",
                dataset, PlotOrientation.VERTICAL, false, true, false
        );

        return chart;
    }
}
