package com.virtuallab.auth;

import com.virtuallab.database.UserDAO;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }

    public int login(String username, String password) {

        String hashedPassword = HashUtil.hashPassword(password);

        int userId = userDAO.getUserIdIfValid(username, hashedPassword);

        return userId;
    }

    public boolean register(String username, String password) {

        String hashedPassword = HashUtil.hashPassword(password);
        return userDAO.addUser(username, hashedPassword);
    }
}
