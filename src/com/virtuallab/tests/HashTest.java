package com.virtuallab.tests;

import com.virtuallab.auth.HashUtil;

public class HashTest {
    public static void main(String[] args) {
        String password1 = HashUtil.hashPassword("03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4");
        String password2 = HashUtil.hashPassword("1234");

        System.out.println("First Hash: " + password1);
        System.out.println("Second Hash: " + password2);
        System.out.println("Hashes Match: " + password1.equals(password2));
    }
}
