package FinancialHungerGame.item.consumables;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.item.Rechargeable;
import FinancialHungerGame.utility.DisplayHub;

public class AncientVial implements Rechargeable {
    @Override
    public void use(Hero hero) {
        String gold = DisplayHub.gold;
        String BOLD = DisplayHub.BOLD;
        String reset = DisplayHub.reset;
        
        int healthToRecover = 100 - hero.getHealth();
        int enduranceToRecover = 100 - hero.getEndurance();
        
        hero.changeHealth(healthToRecover);
        hero.changeEndurance(enduranceToRecover);
        
        DisplayHub.slowPrint(gold + BOLD + "-> The Ancient Vial glows brightly, instantly restoring all your Health and Endurance!\n" + reset, 10);
    }
    
    @Override
    public String getItemName() {
        return DisplayHub.neongold + "Ancient Vial (FULL RECOVERY)" + DisplayHub.reset;
    }
}
