package game.items;

import game.characters.Hero;
import game.utils.ConsoleHelper;
import java.util.Random;

public class PaleRoot implements Rechargeable {
    @Override
    public void use(Hero hero) {
        String green = "\u001B[32m";
        String reset = "\u001B[0m";
        int gain = new Random().nextInt(10) + 5; 
        hero.changeHealth(gain);
        hero.changeEndurance(gain);
        ConsoleHelper.slowPrint(String.format(green +"-> The Pale Root provided a minor boost: +%d Health and +%d Endurance.\n"+ reset, gain, gain), 10);
    }
    @Override
    public String getItemName() {
        String BOLD = "\u001B[1m";
        String grey = "\u001B[90m";
        String reset = "\u001B[0m";
        return BOLD + grey +"Pale Root" + reset; }
}
