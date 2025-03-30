package com.virtuallab.modulation;

public class ModulationProcessor {
    private static double[] lastModulatedSignal;  // Stores the last modulated signal

    public static double[] modulate(String bitstream, String modulationType, double carrierFreq, double tb, int fs) {
        switch (modulationType) {
            case "FSK":
                lastModulatedSignal = fskModulation(bitstream, carrierFreq, tb, fs);
                break;
            case "BPSK":
                lastModulatedSignal = bpskModulation(bitstream, carrierFreq, tb, fs);
                break;
            case "QPSK":
                lastModulatedSignal = qpskModulation(bitstream, carrierFreq, tb, fs);
                break;
            default:
                throw new IllegalArgumentException("Invalid modulation type");
        }
        return lastModulatedSignal;
    }

    public static String demodulate(String modulationType, double carrierFreq, double tb, int fs) {
        if (lastModulatedSignal == null) {
            throw new IllegalStateException("No modulated signal found! Perform modulation first.");
        }

        switch (modulationType) {
            case "FSK":
                return fskDemodulation(lastModulatedSignal, carrierFreq, tb, fs);
            case "BPSK":
                return bpskDemodulation(lastModulatedSignal, carrierFreq, tb, fs);
            case "QPSK":
                return qpskDemodulation(lastModulatedSignal, carrierFreq, tb, fs);
            default:
                throw new IllegalArgumentException("Invalid modulation type");
        }
    }

    public static double[] getLastModulatedSignal() {
        return lastModulatedSignal;
    }

    private static double[] fskModulation(String bitstream, double fc, double tb, int fs) {
        int samplesPerBit = (int) (fs * tb);
        double[] signal = new double[samplesPerBit * bitstream.length()];
        double f0 = fc; // Frequency for bit '0'
        double f1 = fc * 1.5; // Frequency for bit '1'

        for (int i = 0; i < bitstream.length(); i++) {
            double freq = (bitstream.charAt(i) == '1') ? f1 : f0;
            for (int j = 0; j < samplesPerBit; j++) {
                signal[i * samplesPerBit + j] = Math.sin(2 * Math.PI * freq * j / fs);
            }
        }
        return signal;
    }

    private static double[] bpskModulation(String bitstream, double fc, double tb, int fs) {
        int samplesPerBit = (int) (fs * tb);
        double[] signal = new double[samplesPerBit * bitstream.length()];

        for (int i = 0; i < bitstream.length(); i++) {
            double phase = (bitstream.charAt(i) == '1') ? 0 : Math.PI;
            for (int j = 0; j < samplesPerBit; j++) {
                signal[i * samplesPerBit + j] = Math.sin(2 * Math.PI * fc * j / fs + phase);
            }
        }
        return signal;
    }

    private static double[] qpskModulation(String bitstream, double fc, double tb, int fs) {
        int samplesPerSymbol = (int) (fs * tb);
        double[] signal = new double[samplesPerSymbol * (bitstream.length() / 2)];

        for (int i = 0; i < bitstream.length(); i += 2) {
            double phase = 0;
            if (i + 1 < bitstream.length()) {
                String symbol = bitstream.substring(i, i + 2);
                switch (symbol) {
                    case "00": phase = 0; break;
                    case "01": phase = Math.PI / 2; break;
                    case "10": phase = Math.PI; break;
                    case "11": phase = 3 * Math.PI / 2; break;
                }
            }
            for (int j = 0; j < samplesPerSymbol; j++) {
                signal[(i / 2) * samplesPerSymbol + j] = Math.sin(2 * Math.PI * fc * j / fs + phase);
            }
        }
        return signal;
    }

    private static String fskDemodulation(double[] modulatedSignal, double fc, double tb, int fs) {
        int samplesPerBit = (int) (fs * tb);
        StringBuilder bitstream = new StringBuilder();

        for (int i = 0; i < modulatedSignal.length; i += samplesPerBit) {
            double sum0 = 0, sum1 = 0;
            for (int j = 0; j < samplesPerBit; j++) {
                sum0 += Math.sin(2 * Math.PI * fc * j / fs) * modulatedSignal[i + j];
                sum1 += Math.sin(2 * Math.PI * (fc * 1.5) * j / fs) * modulatedSignal[i + j];
            }
            bitstream.append((sum1 > sum0) ? "1" : "0");
        }
        return bitstream.toString();
    }

    private static String bpskDemodulation(double[] modulatedSignal, double fc, double tb, int fs) {
        int samplesPerBit = (int) (fs * tb);
        StringBuilder bitstream = new StringBuilder();

        for (int i = 0; i < modulatedSignal.length; i += samplesPerBit) {
            double sum = 0;
            for (int j = 0; j < samplesPerBit; j++) {
                sum += Math.sin(2 * Math.PI * fc * j / fs) * modulatedSignal[i + j];
            }
            bitstream.append((sum >= 0) ? "1" : "0");
        }
        return bitstream.toString();
    }

    private static String qpskDemodulation(double[] modulatedSignal, double fc, double tb, int fs) {
        int samplesPerSymbol = (int) (fs * tb);
        StringBuilder bitstream = new StringBuilder();

        for (int i = 0; i < modulatedSignal.length; i += samplesPerSymbol) {
            double sumI = 0, sumQ = 0;
            for (int j = 0; j < samplesPerSymbol; j++) {
                sumI += Math.sin(2 * Math.PI * fc * j / fs) * modulatedSignal[i + j];
                sumQ += Math.cos(2 * Math.PI * fc * j / fs) * modulatedSignal[i + j];
            }

            if (sumI >= 0 && sumQ >= 0) bitstream.append("00");
            else if (sumI < 0 && sumQ >= 0) bitstream.append("01");
            else if (sumI < 0 && sumQ < 0) bitstream.append("10");
            else bitstream.append("11");
        }
        return bitstream.toString();
    }
}
