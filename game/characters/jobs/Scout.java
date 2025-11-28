package game.characters.jobs;

import game.characters.Hero;
import game.utils.ConsoleHelper;
import java.util.Random;

public class Scout extends Hero {
    public Scout(String name) {
        super(name, 75, 100, 4, 9);
    }

    @Override
    public void fight(String enemy) {
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        Random rand = new Random();
        int baseDamage = 5;
        int damageDealt = rand.nextInt(baseDamage) + this.getAgility(); 
        
        int damageTaken = rand.nextInt(10) - (this.getAgility() * 2); 
        if (damageTaken < 0) damageTaken = 0;

        ConsoleHelper.slowPrint(String.format(pink + "%s focuses on exhausting the %s with hit-and-run tactics.\n" + reset, getName(), enemy), 10);
        ConsoleHelper.slowPrint(String.format(pink + "Dealt %d damage. They only managed to scratch you for %d damage.\n" + reset, damageDealt, damageTaken), 10);

        changeHealth(-damageTaken);
        changeEndurance(-5); 
    }
}
