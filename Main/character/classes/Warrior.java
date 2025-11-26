package FinancialHungerGame.character.classes;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.utility.DisplayHub;
import java.util.Random;

public class Warrior extends Hero {
    public Warrior(String name) {
        super(name, 100, 70, 9, 5);
    }

    @Override
    public void fight(String enemy) {
        String pink = DisplayHub.pink;
        String reset = DisplayHub.reset;
        Random rand = DisplayHub.getRandom();
        int baseDamage = 15;
        int damageDealt = rand.nextInt(baseDamage) + this.getStrength() * 2; 
        
        int damageTaken = rand.nextInt(20) - (this.getAgility() / 2); 
        if (damageTaken < 5) damageTaken = 5;

        DisplayHub.slowPrint(String.format(pink + "%s performs a powerful ground smash against the %s!\n" + reset, getName(), enemy), 10);
        DisplayHub.slowPrint(String.format(pink + "Dealt %d damage, but took %d damage in return.\n" + reset, damageDealt, damageTaken), 10);

        changeHealth(-damageTaken);
        changeEndurance(-20); 
    }
}
