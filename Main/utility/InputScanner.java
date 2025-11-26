package FinancialHungerGame.utility;

import java.util.Scanner;

public class InputScanner {
    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }
    
    public static int getIntInput(int min, int max) {
        String BOLD = DisplayHub.BOLD;
        String red = DisplayHub.red;
        String reset = DisplayHub.reset;
        int choice = -1;
        while (choice < min || choice > max) {
            DisplayHub.slowPrint("> Enter your choice (" + min + "-" + max + "): ", 0);
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice < min || choice > max) {
                    DisplayHub.slowPrint(BOLD + red + "--- Invalid choice. Please enter a number between " + min + " and " + max + ".\n" + reset, 10);
                }
            } catch (NumberFormatException e) {
                DisplayHub.slowPrint(BOLD + red + "--- Invalid input. Please enter a valid number.\n" + reset, 10);
            }
        }
        return choice;
    }
}
