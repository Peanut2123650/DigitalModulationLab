package com.virtuallab.utils;

import java.util.Arrays;

public class SignalProcessor {

    public static String asciiToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            String bits = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binary.append(bits);
        }
        return binary.toString();
    }

    public static String binaryToAscii(String bitstream) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < bitstream.length(); i += 8) {
            String byteString = bitstream.substring(i, Math.min(i + 8, bitstream.length()));
            int charCode = Integer.parseInt(byteString, 2);
            text.append((char) charCode);
        }
        return text.toString();
    }

    public static double[] generateDigitalDataWaveform(String bitstream, int fs, double tb) {
        int length = bitstream.length();
        double[] waveform = new double[length * (int) (fs * tb)];
        for (int i = 0; i < length; i++) {
            double value = bitstream.charAt(i) == '1' ? 1.0 : 0.0;
            Arrays.fill(waveform, i * (int) (fs * tb), (i + 1) * (int) (fs * tb), value);
        }
        return waveform;
    }

    public static double[] generateCarrierWaveform(double frequency, double amplitude, int fs, double tb, int numBits) {
        int numSamples = (int) (fs * tb * numBits);
        double[] carrierSignal = new double[numSamples];
        for (int i = 0; i < numSamples; i++) {
            carrierSignal[i] = amplitude * Math.sin(2 * Math.PI * frequency * (i / (double) fs));
        }
        return carrierSignal;
    }
}
