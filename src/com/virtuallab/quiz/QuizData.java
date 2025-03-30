package com.virtuallab.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizData {
    private static final String[] QUESTIONS = {
            "What is the primary advantage of Amplitude Modulation (AM)?",
            "Which component varies in Frequency Modulation (FM)?",
            "In AM, what remains constant?",
            "Which modulation type is least affected by noise?",
            "What does Frequency Modulation (FM) primarily improve?",
            "In AM, increasing modulation depth increases...",
            "Which modulation technique is used in FM radio broadcasting?",
            "Phase Modulation (PM) is closely related to which other modulation?",
            "What happens to FM bandwidth when frequency deviation increases?",
            "Which modulation scheme is used in television broadcasting?",
            "In AM, if carrier frequency is increased, what happens to bandwidth?",
            "What is the typical bandwidth of an AM signal?",
            "In PM, phase shift depends on...",
            "Which modulation technique is most resistant to multipath interference?",
            "FM signals require a larger...",
            "Which modulation technique is used in two-way radio communications?",
            "In AM transmission, the carrier signal is...",
            "Which parameter remains constant in Frequency Modulation (FM)?",
            "Phase Modulation (PM) is widely used in...",
            "Which of the following modulations uses frequency deviation as a parameter?"
    };

    private static final String[][] OPTIONS = {
            {"Better noise resistance", "Lower bandwidth", "Lower power consumption", "Less distortion"},
            {"Amplitude", "Phase", "Frequency", "Voltage"},
            {"Amplitude", "Phase", "Frequency", "Power"},
            {"AM", "FM", "PM", "All of the above"},
            {"Signal quality", "Power efficiency", "Signal amplitude", "Carrier frequency"},
            {"Bandwidth", "Power", "Efficiency", "Noise"},
            {"AM", "FM", "PM", "PCM"},
            {"AM", "FM", "PCM", "FM"},
            {"Decreases", "Increases", "Remains constant", "Becomes zero"},
            {"FM", "AM", "PM", "PCM"},
            {"Decreases", "Increases", "Remains the same", "Depends on power"},
            {"10 kHz", "200 kHz", "100 kHz", "50 kHz"},
            {"Signal amplitude", "Signal frequency", "Modulation index", "Carrier wave"},
            {"FM", "AM", "PM", "PCM"},
            {"Bandwidth", "Power", "Amplitude", "Phase"},
            {"FM", "AM", "PM", "PCM"},
            {"Removed", "Maintained", "Amplified", "Suppressed"},
            {"Phase", "Frequency", "Amplitude", "Noise"},
            {"AM transmission", "Radar", "Satellite Communication", "PM broadcasting"},
            {"FM", "AM", "PM", "PCM"}
    };

    private static final int[] CORRECT_ANSWERS = {1, 3, 3, 2, 1, 1, 2, 2, 2, 2, 1, 1, 3, 1, 1, 2, 2, 2, 3, 1};

    public static List<Integer> getRandomQuestions(int count) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < QUESTIONS.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
        return indices.subList(0, Math.min(count, QUESTIONS.length));
    }

    public static String getQuestion(int index) {
        return QUESTIONS[index];
    }

    public static String[] getOptions(int index) {
        return OPTIONS[index];
    }

    public static int getCorrectAnswer(int index) {
        return CORRECT_ANSWERS[index];
    }
}
