package game.characters.jobs;

import game.characters.Hero;
import game.utils.ConsoleHelper;
import java.util.Random;

public class Warrior extends Hero {
    public Warrior(String name) {
        super(name, 100, 70, 9, 5);
    }

    @Override
    public void fight(String enemy) {
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        Random rand = new Random();
        int baseDamage = 15;
        int damageDealt = rand.nextInt(baseDamage) + this.getStrength() * 2; 
        
        int damageTaken = rand.nextInt(20) - (this.getAgility() / 2); 
        if (damageTaken < 5) damageTaken = 5;

        ConsoleHelper.slowPrint(String.format(pink + "%s performs a powerful ground smash against the %s!\n" + reset, getName(), enemy), 10);
        ConsoleHelper.slowPrint(String.format(pink + "Dealt %d damage, but took %d damage in return.\n" + reset, damageDealt, damageTaken), 10);

        changeHealth(-damageTaken);
        changeEndurance(-20); 
    }
}
