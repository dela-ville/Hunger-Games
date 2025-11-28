package game.items;

import game.characters.Hero;
import game.utils.ConsoleHelper;
import java.util.Random;

public class GlowingApple implements Rechargeable {
    @Override
    public void use(Hero hero) {
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        int healthGain = new Random().nextInt(30) + 10; 
        hero.changeHealth(healthGain);
        ConsoleHelper.slowPrint(String.format(pink + "-> The Glowing Apple restored %d Health!\n" + reset, healthGain), 10);
    }
    @Override
    public String getItemName() {
        String BOLD = "\u001B[1m";
        String red = "\u001B[31m";
        String orange = "\u001B[38;2;255;165;0m";
        String yellow = "\u001B[33m";
        String green = "\u001B[32m";
        String blue = "\u001B[34m";
        String indigo = "\u001B[38;2;75;0;130m";
        String purple = "\u001B[35m";
        String reset = "\u001B[0m";
        return BOLD + red + "G" + orange + "l" + yellow + "o" + green + "w" + blue + "i" + indigo + "n" + purple + "g" + red + " A" + orange + "p" + yellow + "p" + green + "l" + blue + "e" + reset; 
    }
}
