package game.items;

import game.characters.Hero;
import game.utils.ConsoleHelper;

public class AncientVial implements Rechargeable {
    @Override
    public void use(Hero hero) {
        int healthGain = 30;
        int enduranceGain = 30;
        hero.changeHealth(healthGain);
        hero.changeEndurance(enduranceGain);
        ConsoleHelper.slowPrint(String.format("-> The Ancient Vial provides a major boost! +%d Health and +%d Endurance.\n", healthGain, enduranceGain), 10);
    }
    @Override
    public String getItemName() {
        String BOLD = "\u001B[1m";
        String neongold = "\u001B[38;2;255;215;0m";
        String reset = "\u001B[0m";
        return BOLD + neongold +  "Ancient Vial"; }
}
