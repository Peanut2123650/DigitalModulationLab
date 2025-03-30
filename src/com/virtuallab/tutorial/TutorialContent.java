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
                "<li>Used in AM radio (535-1705 kHz)</li></ul></div>" +
                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Frequency Modulation (FM)</h3>" +
                "<ul><li>Frequency varies with message signal</li>" +
                "<li>Amplitude remains constant</li>" +
                "<li>Used in FM radio (88-108 MHz)</li></ul></div>" +
                "<h2 style='color:#4682B4; margin-top:25px;'>Digital Modulation</h2>" +
                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Frequency Shift Keying (FSK)</h3>" +
                "<ul><li>Digital data represented by frequency shifts</li>" +
                "<li>'1' with one frequency, '0' with another</li>" +
                "<li>Used in modems, RFID</li></ul></div>" +
                "<div style='background:#FFFFFF; border-left:4px solid #6495ED; padding:10px 15px; margin:10px 0;'>" +
                "<h3 style='color:#6495ED;'>Binary Phase Shift Keying (BPSK)</h3>" +
                "<ul><li>Digital data transmitted via phase shifts</li>" +
                "<li>More noise-resistant than FSK</li>" +
                "<li>Used in Wi-Fi, satellite communication</li></ul></div>" +
                "</div></body></html>";
    }
}
