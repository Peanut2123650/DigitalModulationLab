package com.virtuallab;

import javax.swing.SwingUtilities;
import com.virtuallab.auth.LoginPage;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
