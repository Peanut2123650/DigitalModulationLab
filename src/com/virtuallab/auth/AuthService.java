package com.virtuallab.auth;

import com.virtuallab.database.UserDAO;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public int login(String username, String password) {
        // Hash the entered password
        String hashedPassword = HashUtil.hashPassword(password);

        // Validate user and retrieve user ID
        int userId = userDAO.getUserIdIfValid(username, hashedPassword);

        return userId; // Returns -1 if login fails
    }

    public boolean register(String username, String password) {
        // Hash the password before storing it
        String hashedPassword = HashUtil.hashPassword(password);
        return userDAO.addUser(username, hashedPassword);
    }
}
