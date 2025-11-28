package game.utils;

public class ConsoleHelper {
    /**
     * Utility method to print text character-by-character with a delay.
     */
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
}
