package game.items;

import game.characters.Hero;
import game.utils.ConsoleHelper;
import java.util.Random;

public class ShadowyBerries implements Rechargeable {
    @Override
    public void use(Hero hero) {
        int enduranceGain = new Random().nextInt(10) + 5; 
        hero.changeEndurance(enduranceGain);
        ConsoleHelper.slowPrint(String.format("-> The Shadowy Berries restored %d Endurance!\n", enduranceGain), 10);
    }
    @Override
    public String getItemName() { 
       String BOLD = "\u001B[1m";
        String purple = "\u001B[35m";
        String reset = "\u001B[0m"; 
        return BOLD + purple +"Shadowy Berries" + reset; }
}
