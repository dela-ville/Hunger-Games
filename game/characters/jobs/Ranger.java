package game.characters.jobs;

import game.characters.Hero;
import game.utils.ConsoleHelper;
import java.util.Random;

public class Ranger extends Hero {
    public Ranger(String name) {
        super(name, 85, 90, 6, 8); 
    }

    @Override
    public void fight(String enemy) {
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        Random rand = new Random();
        int baseDamage = 10;
        int damageDealt = rand.nextInt(baseDamage) + this.getAgility() * 2; 
        
        int damageTaken = rand.nextInt(15) - (this.getAgility()); 
        if (damageTaken < 0) damageTaken = 0;

        ConsoleHelper.slowPrint(String.format(pink + "%s uses their quick evasion and ranged attack on the %s!\n" + reset, getName(), enemy), 10);
        ConsoleHelper.slowPrint(String.format(pink + "Dealt %d damage, and swiftly avoided taking major damage, only %d.\n" + reset, damageDealt, damageTaken), 10);

        changeHealth(-damageTaken);
        changeEndurance(-10); 
    }
}
