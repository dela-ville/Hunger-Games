package FinancialHungerGame.item.consumables;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.item.Rechargeable;
import FinancialHungerGame.utility.DisplayHub;
import java.util.Random;

public class ShadowyBerries implements Rechargeable {
    @Override
    public void use(Hero hero) {
        String pink = DisplayHub.pink;
        String reset = DisplayHub.reset;
        Random random = DisplayHub.getRandom();
        
        int enduranceGain = random.nextInt(25) + 5; 
        hero.changeEndurance(enduranceGain);
        
        DisplayHub.slowPrint(String.format(pink + "-> The Shadowy Berries restored %d Endurance!\n" + reset, enduranceGain), 10);
    }
    
    @Override
    public String getItemName() {
        return DisplayHub.purple + "Shadowy Berries (Medium Endurance)" + DisplayHub.reset;
    }
}
