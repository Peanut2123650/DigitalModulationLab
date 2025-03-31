package com.virtuallab.database;

public class Experiment {
    private int id, userId, samplingFrequency;
    private String experimentName, inputText, modulationType, demodulatedText;
    private double carrierFrequency, amplitude, bitDuration;

    public Experiment(int id, int userId, String experimentName, String inputText,
                      double carrierFrequency, double amplitude, int samplingFrequency,
                      double bitDuration, String modulationType, String demodulatedText) {
        this.id = id;
        this.userId = userId;
        this.experimentName = experimentName;
        this.inputText = inputText;
        this.carrierFrequency = carrierFrequency;
        this.amplitude = amplitude;
        this.samplingFrequency = samplingFrequency;
        this.bitDuration = bitDuration;
        this.modulationType = modulationType;
        this.demodulatedText = demodulatedText;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getExperimentName() { return experimentName; }
    public String getInputText() { return inputText; }
    public double getCarrierFrequency() { return carrierFrequency; }
    public double getAmplitude() { return amplitude; }
    public int getSamplingFrequency() { return samplingFrequency; }
    public double getBitDuration() { return bitDuration; }
    public String getModulationType() { return modulationType; }
    public String getDemodulatedText() { return demodulatedText; }
}
