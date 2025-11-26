package FinancialHungerGame.item.consumables;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.item.Rechargeable;
import FinancialHungerGame.utility.DisplayHub;
import java.util.Random;

public class GlowingApple implements Rechargeable {
    @Override
    public void use(Hero hero) {
        String pink = DisplayHub.pink;
        String reset = DisplayHub.reset;
        Random random = DisplayHub.getRandom();
        
        int healthGain = random.nextInt(30) + 10; 
        hero.changeHealth(healthGain);
        
        DisplayHub.slowPrint(String.format(pink + "-> The Glowing Apple restored %d Health!\n" + reset, healthGain), 10);
    }
    
    @Override
    public String getItemName() {
        // Using DisplayHub for all colors
        return DisplayHub.BOLD + DisplayHub.red + "G" + DisplayHub.orange + "l" + DisplayHub.yellow + "o" + DisplayHub.green + "w" + DisplayHub.blue + "i" + DisplayHub.indigo + "n" + DisplayHub.purple + "g" + DisplayHub.red + " A" + DisplayHub.orange + "p" + DisplayHub.yellow + "p" + DisplayHub.green + "l" + DisplayHub.blue + "e" + DisplayHub.reset; 
    }
}
