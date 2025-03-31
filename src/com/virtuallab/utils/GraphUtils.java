package com.virtuallab.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

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

        // Custom styling
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(230, 230, 230));  // Light gray background
        plot.setDomainGridlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(30, 144, 255)); // Deep blue
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // Thicker lines
        plot.setRenderer(renderer);

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

        // Custom styling
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(230, 230, 230));
        plot.setDomainGridlinePaint(Color.DARK_GRAY);
        plot.setRangeGridlinePaint(Color.DARK_GRAY);

        XYStepRenderer renderer = new XYStepRenderer();
        renderer.setSeriesPaint(0, new Color(255, 69, 0)); // Bright red for contrast
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        return chart;
    }
}
