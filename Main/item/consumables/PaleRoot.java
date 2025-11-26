package FinancialHungerGame.item.consumables;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.item.Rechargeable;
import FinancialHungerGame.utility.DisplayHub;
import java.util.Random;

public class PaleRoot implements Rechargeable {
    @Override
    public void use(Hero hero) {
        String pink = DisplayHub.pink;
        String reset = DisplayHub.reset;
        Random random = DisplayHub.getRandom();
        
        int healthGain = random.nextInt(15) + 5;
        int enduranceGain = random.nextInt(15) + 5; 
        
        hero.changeHealth(healthGain);
        hero.changeEndurance(enduranceGain);
        
        DisplayHub.slowPrint(String.format(pink + "-> The Pale Root restored %d Health and %d Endurance!\n" + reset, healthGain, enduranceGain), 10);
    }
    
    @Override
    public String getItemName() {
        return DisplayHub.white + "Pale Root (Balanced Recovery)" + DisplayHub.reset;
    }
}
