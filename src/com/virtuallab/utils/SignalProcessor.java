package com.virtuallab.utils;

public class SignalProcessor {

    // Convert ASCII text to binary bitstream
    public static String asciiToBinary(String text) {
        StringBuilder binary = new StringBuilder();
        for (char c : text.toCharArray()) {
            String bits = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binary.append(bits);
        }
        return binary.toString();
    }

    // Convert binary bitstream to ASCII text
    public static String binaryToAscii(String bitstream) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < bitstream.length(); i += 8) {
            String byteString = bitstream.substring(i, Math.min(i + 8, bitstream.length()));
            int charCode = Integer.parseInt(byteString, 2);
            text.append((char) charCode);
        }
        return text.toString();
    }
}
