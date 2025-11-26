package FinancialHungerGame.character.classes;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.utility.DisplayHub;
import java.util.Random;

public class Ranger extends Hero {
    public Ranger(String name) {
        super(name, 85, 90, 6, 8); 
    }

    @Override
    public void fight(String enemy) {
        String pink = DisplayHub.pink; 
        String reset = DisplayHub.reset;
        Random rand = DisplayHub.getRandom();
        int baseDamage = 10;
        int damageDealt = rand.nextInt(baseDamage) + this.getAgility() * 2; 
        
        int damageTaken = rand.nextInt(15) - (this.getAgility()); 
        if (damageTaken < 0) damageTaken = 0;

        DisplayHub.slowPrint(String.format(pink + "%s uses their quick evasion and ranged attack on the %s!\n" + reset, getName(), enemy), 10);
        DisplayHub.slowPrint(String.format(pink + "Dealt %d damage, and swiftly avoided taking major damage, only %d.\n" + reset, damageDealt, damageTaken), 10);

        changeHealth(-damageTaken);
        changeEndurance(-10); 
    }
}
