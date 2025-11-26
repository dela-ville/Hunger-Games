package FinancialHungerGame.utility;

import java.util.Random;

public class DisplayHub {
    // --- ANSI Color Definitions (Complete set from Main.java) ---
    public static final String BOLD = "\u001B[1m";
    public static final String reset = "\u001B[0m";
    public static final String navy = "\u001B[38;2;0;0;128m";
    public static final String dblue = "\u001B[38;2;0;0;90m";
    public static final String neongreen = "\u001B[38;2;57;255;20m";
    public static final String neonblue = "\u001B[38;2;0;150;255m";
    public static final String neonpink = "\u001B[38;2;255;20;147m";
    public static final String neongold = "\u001B[38;2;255;215;0m";
    public static final String gold = "\u001B[38;2;212;175;55m";
    public static final String neonsilver = "\u001B[38;2;224;224;224m";
    public static final String silver = "\u001B[38;2;192;192;192m";
    public static final String sky = "\u001B[38;2;135;206;235m";
    public static final String red = "\u001B[31m";
    public static final String orange = "\u001B[38;2;255;165;0m";
    public static final String yellow = "\u001B[33m";
    public static final String green = "\u001B[32m";
    public static final String blue = "\u001B[34m";
    public static final String indigo = "\u001B[38;2;75;0;130m";
    public static final String purple = "\u001B[35m";
    public static final String pink = "\u001B[38;2;255;182;193m"; 
    public static final String grey = "\u001B[90m";
    public static final String white = "\u001B[37m";


    private static final Random random = new Random();

    public static void slowPrint(String text, long delayMs) {
        for (char character : text.toCharArray()) {
            System.out.print(character);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        }
    }
    
    public static Random getRandom() {
        return random;
    }
}
