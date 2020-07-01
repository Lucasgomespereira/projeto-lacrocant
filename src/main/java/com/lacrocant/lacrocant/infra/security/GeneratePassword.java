package com.lacrocant.lacrocant.infra.security;

import java.util.Random;

public class GeneratePassword {

    private static Character[] chart;

    static {
        Character[] chartNew = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
                'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z' };
        chart = chartNew;
    }

    /**
     * Create a chracter sequence of 6 numbers
     * 
     * @return
     */
    public static String createNewPassword() {
        return createNewPassword(6);
    }

    /**
     * Create a chracter sequence with size passed
     */
    public static String createNewPassword(int size) {
        final int lengthPasswd = size;
        char[] newPasswd = new char[lengthPasswd];
        int chartLenght = chart.length;
        Random rdm = new Random();
        for (int x = 0; x < lengthPasswd; x++) {
            newPasswd[x] = chart[rdm.nextInt(chartLenght)];
        }
        return new String(newPasswd).toLowerCase();
    }
    
}