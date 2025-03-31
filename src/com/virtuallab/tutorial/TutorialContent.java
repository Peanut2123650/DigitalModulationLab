package com.virtuallab.tutorial;

public class TutorialContent {
    public static String getContent() {
        return "<html><body style='font-family:Arial; font-size:14px; color:#333; line-height:1.6;'>" +
                "<div style='max-width:800px; margin:0 auto;'>" +
                "<h1 style='color:#4682B4; border-bottom:2px solid #4682B4; padding-bottom:5px;'>Digital Modulation Techniques</h1>" +
                "<h2 style='color:#4682B4; margin-top:25px;'>Analog Modulation</h2>" +
                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Amplitude Modulation (AM)</h3>" +
                "<ul><li>Amplitude varies with message signal</li>" +
                "<li>Frequency remains constant</li>" +
                "<li>Used in AM radio (535-1705 kHz)</li>" +
                "<li><b>Bandwidth:</b> Twice the highest modulating frequency</li></ul></div>" +
                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Frequency Modulation (FM)</h3>" +
                "<ul><li>Frequency varies with message signal</li>" +
                "<li>Amplitude remains constant</li>" +
                "<li>Used in FM radio (88-108 MHz)</li>" +
                "<li><b>Noise Immunity:</b> Better than AM due to constant amplitude</li></ul></div>" +

                "<h2 style='color:#4682B4; margin-top:25px;'>Digital Modulation</h2>" +
                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Frequency Shift Keying (FSK)</h3>" +
                "<ul><li>Digital data represented by frequency shifts</li>" +
                "<li>'1' with one frequency (mark), '0' with another (space)</li>" +
                "<li><b>Applications:</b> Modems, RFID, Bluetooth (GFSK variant)</li>" +
                "<li><b>Bandwidth:</b> Higher than PSK for same data rate</li>" +
                "<li><b>Types:</b> MSK, GFSK, Sunde's FSK</li></ul></div>" +

                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Binary Phase Shift Keying (BPSK)</h3>" +
                "<ul><li>Uses two phase shifts (0° and 180°) to represent binary data</li>" +
                "<li><b>Advantage:</b> More noise-resistant than FSK</li>" +
                "<li><b>Bandwidth:</b> Equal to bit rate (Rb)</li>" +
                "<li><b>Detection:</b> Requires carrier synchronization (coherent detection)</li>" +
                "<li><b>Variant:</b> Differential BPSK doesn't need carrier recovery</li>" +
                "<li><b>Applications:</b> Wi-Fi, satellite communication</li></ul></div>" +

                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Quadrature Phase Shift Keying (QPSK)</h3>" +
                "<ul><li>Transmits 2 bits per symbol (4 phase shifts)</li>" +
                "<li><b>Efficiency:</b> Double the data rate of BPSK in same bandwidth</li>" +
                "<li><b>Constellation:</b> Square diagram with four points</li>" +
                "<li><b>Error Correction:</b> Often paired with convolutional coding</li>" +
                "<li><b>Phase Shifts:</b> Typically 45°, 135°, 225°, 315°</li>" +
                "<li><b>Applications:</b> Satellite communication, digital TV</li></ul></div>" +

                "<div style='background:#f8f9fa; border:1px solid #ddd; padding:15px; margin-top:20px;'>" +
                "<h3 style='color:#4682B4;'>Comparison Summary</h3>" +
                "<table style='width:100%; border-collapse:collapse;'>" +
                "<tr style='background:#e9ecef;'><th style='padding:8px; border:1px solid #ddd;'>Modulation</th>" +
                "<th style='padding:8px; border:1px solid #ddd;'>Bits/Symbol</th>" +
                "<th style='padding:8px; border:1px solid #ddd;'>Bandwidth</th>" +
                "<th style='padding:8px; border:1px solid #ddd;'>Noise Immunity</th></tr>" +
                "<tr><td style='padding:8px; border:1px solid #ddd;'>FSK</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>1</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>Highest</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>Medium</td></tr>" +
                "<tr style='background:#f8f9fa;'><td style='padding:8px; border:1px solid #ddd;'>BPSK</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>1</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>Medium</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>High</td></tr>" +
                "<tr><td style='padding:8px; border:1px solid #ddd;'>QPSK</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>2</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>Lowest</td>" +
                "<td style='padding:8px; border:1px solid #ddd;'>High</td></tr>" +
                "</table></div></div></body></html>";
    }
}
