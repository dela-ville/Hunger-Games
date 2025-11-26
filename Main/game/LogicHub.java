package FinancialHungerGame.game;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.item.Rechargeable;
import FinancialHungerGame.item.consumables.AncientVial;
import FinancialHungerGame.item.consumables.GlowingApple;
import FinancialHungerGame.item.consumables.PaleRoot;
import FinancialHungerGame.item.consumables.ShadowyBerries;
import FinancialHungerGame.utility.DisplayHub;
import FinancialHungerGame.utility.InputScanner;

import java.util.Random;

public class LogicHub {
    
    private static final Random random = DisplayHub.getRandom();
    
    private static final Rechargeable[] RESOURCE_OPTIONS = {
        new GlowingApple(),
        new ShadowyBerries(),
        new PaleRoot()
    };
    
    private static final String[] ENEMIES = {
        "Mountain Lion", "Dire Wolf", "Goblin Scout", "Giant Spider", "Cave Troll", 
        "Shadow Beast", "Swarm of Vipers", "Armored Golem"
    };

  
    private static void runItemFindFruits(Hero hero) {
        String BOLD = DisplayHub.BOLD;
        String reset = DisplayHub.reset;

        DisplayHub.slowPrint("\nIt appears to be a cache of healing items.\n", 10);
        DisplayHub.slowPrint(BOLD + "Choose an item to consume:\n" + reset, 10);
        
        for (int i = 0; i < RESOURCE_OPTIONS.length; i++) {
            DisplayHub.slowPrint(String.format("(%d) %s\n", i + 1, RESOURCE_OPTIONS[i].getItemName()), 10);
        }
        DisplayHub.slowPrint("(4) Leave it all (Gain +5 Endurance)\n", 10);

        int choice = InputScanner.getIntInput(1, 4);

        if (choice >= 1 && choice <= 3) {
            RESOURCE_OPTIONS[choice - 1].use(hero);
        } else if (choice == 4) {
            hero.changeEndurance(5);
            DisplayHub.slowPrint("-> You conserved your strength and gained " + BOLD + "+5 Endurance" + reset + ".\n", 10);
        }
    }
    
    private static void runItemFindWater(Hero hero) {
        String BOLD = DisplayHub.BOLD;
        String reset = DisplayHub.reset;
        String green = DisplayHub.green;
        String red = DisplayHub.red;
        
        DisplayHub.slowPrint("\nThe water looks clean and safe. This is a chance to recover.\n", 10);
        DisplayHub.slowPrint(BOLD + "(1) Rest and Drink" + reset + " (Gain +15 Health, +15 Endurance)\n", 10);
        DisplayHub.slowPrint(BOLD + "(2) Press On" + reset + " (No changes)\n", 10);
        
        int choice = InputScanner.getIntInput(1, 2);
        
        if (choice == 1) {
            hero.changeHealth(15);
            hero.changeEndurance(15);
            DisplayHub.slowPrint(green + "-> You feel refreshed. +15 Health and +15 Endurance gained.\n" + reset, 10);
        } else {
            DisplayHub.slowPrint("-> You kept moving, conserving time.\n", 10);
        }
    }

    private static void runItemFindSingle(Hero hero) {
        String BOLD = DisplayHub.BOLD;
        String reset = DisplayHub.reset;
        
        AncientVial vial = new AncientVial();
        DisplayHub.slowPrint(String.format("You found a powerful item: %s.\n", vial.getItemName()), 10);
        DisplayHub.slowPrint(BOLD + "(1) Use the Ancient Vial" + reset + " (Restores ALL Health and Endurance)\n", 10);
        DisplayHub.slowPrint(BOLD + "(2) Leave it" + reset + "\n", 10);

        int choice = InputScanner.getIntInput(1, 2);

        if (choice == 1) {
            vial.use(hero);
        } else {
            DisplayHub.slowPrint("-> You decided the risk wasn't worth the reward, and left the vial behind.\n", 10);
        }
    }
    
    private static void runItemFindTrap(Hero hero) {
        String BOLD = DisplayHub.BOLD;
        String red = DisplayHub.red;
        String green = DisplayHub.green;
        String reset = DisplayHub.reset;
        
        DisplayHub.slowPrint("\nThis opportunity carries risk. Proceed with caution.\n", 10);
        DisplayHub.slowPrint(BOLD + "(1) Take the chance" + reset + " (50/50: Gain +20 Endurance OR Lose -15 Health)\n", 10);
        DisplayHub.slowPrint(BOLD + "(2) Ignore the risk" + reset + " (No changes)\n", 10);
        
        int choice = InputScanner.getIntInput(1, 2);
        
        if (choice == 1) {
            if (random.nextBoolean()) { // 50% chance of success
                hero.changeEndurance(20);
                DisplayHub.slowPrint(green + "-> Success! You gained " + BOLD + "+20 Endurance" + reset + ".\n" + reset, 10);
            } else {
                hero.changeHealth(-15);
                DisplayHub.slowPrint(red + "-> Failed! You stepped in a trap and lost " + BOLD + "-15 Health" + reset + ".\n" + reset, 10);
            }
        } else {
            DisplayHub.slowPrint("-> You chose safety and continued on.\n", 10);
        }
    }

    private static void runItemFindEvent(Hero hero, GameEvent event) {
        DisplayHub.slowPrint(DisplayHub.BOLD + "\n[RESOURCE EVENT]\n" + DisplayHub.reset, 10);
        DisplayHub.slowPrint(event.description + "\n", 10);

        // Use startsWith to handle all ITEM_FIND subtypes
        if (event.type.equals("ITEM_FIND_FRUITS")) {
            runItemFindFruits(hero);
        } else if (event.type.equals("ITEM_FIND_WATER")) {
            runItemFindWater(hero);
        } else if (event.type.equals("ITEM_FIND_SINGLE")) {
            runItemFindSingle(hero);
        } else if (event.type.equals("ITEM_FIND_TRAP")) {
            runItemFindTrap(hero);
        }
    }

    private static void runCombatEvent(Hero hero) {
        String BOLD = DisplayHub.BOLD;
        String reset = DisplayHub.reset;
        String red = DisplayHub.red;
        
        String enemy = ENEMIES[random.nextInt(ENEMIES.length)];
        DisplayHub.slowPrint(String.format(BOLD + red + "\n[COMBAT EVENT] %s appears!\n" + reset, enemy), 10);

        DisplayHub.slowPrint(BOLD + "What do you do?\n" + reset, 10);
        DisplayHub.slowPrint("(1) Fight\n", 10);
        DisplayHub.slowPrint("(2) Try to flee (Agility Check)\n", 10);
        DisplayHub.slowPrint("(3) Hide (Endurance Check)\n", 10);

        int choice = InputScanner.getIntInput(1, 3);
        
        if (choice == 1) {
            hero.fight(enemy);
        } else if (choice == 2) {
            int agilityCheck = random.nextInt(10) + 1; // 1-10
            if (hero.getAgility() >= agilityCheck) {
                DisplayHub.slowPrint("-> You successfully evaded the threat with your speed!\n", 10);
            } else {
                DisplayHub.slowPrint(red + "-> Flee attempt failed! The enemy strikes as you turn to run, taking a toll on your Health.\n" + reset, 10);
                hero.changeHealth(-(random.nextInt(10) + 5)); // Lose 5-15 Health
            }
        } else if (choice == 3) {
            int enduranceCheck = random.nextInt(10) + 1; // 1-10
            if (hero.getEndurance() >= enduranceCheck) {
                DisplayHub.slowPrint("-> You found a good hiding spot and recovered your strength!\n", 10);
                hero.changeEndurance(10);
            } else {
                DisplayHub.slowPrint(red + "-> Hide attempt failed! You were found and lost a significant amount of Health and Endurance.\n" + reset, 10);
                hero.changeHealth(-(random.nextInt(15) + 5)); // Lose 5-20 Health
                hero.changeEndurance(-15);
            }
        }
    }

    private static void runSkillCheckEvent(Hero hero) {
        String BOLD = DisplayHub.BOLD;
        String red = DisplayHub.red;
        String reset = DisplayHub.reset;
        
        DisplayHub.slowPrint(BOLD + "\n[SKILL CHECK EVENT]\n" + reset, 10);
        DisplayHub.slowPrint(hero.getStatusString() + "\n", 10);
        DisplayHub.slowPrint(hero.getName() + ", " + hero.getClass().getSimpleName() + ", faces a difficult challenge: " + hero.getName() + event.description + "\n", 10);

        DisplayHub.slowPrint(BOLD + "How do you overcome it?\n" + reset, 10);
        DisplayHub.slowPrint("(1) Brute Force (Strength Check)\n", 10);
        DisplayHub.slowPrint("(2) Evasion/Precision (Agility Check)\n", 10);
        DisplayHub.slowPrint("(3) Slow/Steady (Endurance Check)\n", 10);

        int choice = InputScanner.getIntInput(1, 3);
        int requiredCheck = random.nextInt(10) + 1; // 1-10

        boolean success = false;
        
        if (choice == 1) {
            if (hero.getStrength() >= requiredCheck) {
                success = true;
            } else {
                hero.changeHealth(-10);
            }
        } else if (choice == 2) {
            if (hero.getAgility() >= requiredCheck) {
                success = true;
            } else {
                hero.changeHealth(-10);
            }
        } else if (choice == 3) {
            if (hero.getEndurance() >= requiredCheck) {
                success = true;
            } else {
                hero.changeHealth(-10);
            }
        }

        if (success) {
            DisplayHub.slowPrint("-> Success! You overcame the challenge and recovered " + BOLD + "+10 Endurance" + reset + ".\n", 10);
            hero.changeEndurance(10);
        } else {
            DisplayHub.slowPrint(red + "-> Failure! The challenge took a toll, losing " + BOLD + "-10 Health" + reset + ".\n" + reset, 10);
        }
    }

    public static void runDay(Hero hero, GameEvent event) {
        // Use startsWith to cover all ITEM_FIND subtypes (e.g., ITEM_FIND_FRUITS)
        if (event.type.startsWith("ITEM_FIND")) {
            runItemFindEvent(hero, event);
        } else {
          
            switch (event.type) {
                case "COMBAT":
                    runCombatEvent(hero);
                    break;
                case "SKILL":
                    runSkillCheckEvent(hero);
                    break;
            }
        }
    }
}
