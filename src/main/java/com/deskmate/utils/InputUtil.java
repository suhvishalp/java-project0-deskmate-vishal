package com.deskmate.utils;

import java.util.Scanner;

public final class InputUtil {
	
    private static final Scanner sc = new Scanner(System.in);

    private InputUtil() {}

    public static int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(sc.nextLine().trim());
    }

    public static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }
}
