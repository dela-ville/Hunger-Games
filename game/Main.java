package game;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

// Imports mula sa ibang packages
import game.utils.ConsoleHelper;
import game.characters.Hero;
import game.characters.jobs.Warrior;
import game.characters.jobs.Ranger;
import game.characters.jobs.Scout;
import game.items.Rechargeable;
import game.items.GlowingApple;
import game.items.ShadowyBerries;
import game.items.PaleRoot;
import game.items.AncientVial;

public class Main {
    private static final Rechargeable[] RESOURCE_OPTIONS = {
        new GlowingApple(),
        new ShadowyBerries(),
        new PaleRoot()
    };
    
    private static final String[] ENEMIES = {
        "Mountain Lion", "Dire Wolf", "Goblin Scout", "Giant Spider", "Cave Troll", 
        "Shadow Beast", "Swarm of Vipers", "Armored Golem"
    };

    private static final ArrayList<GameEvent> CHALLENGE_EVENTS = new ArrayList<>(Arrays.asList(
        new GameEvent("A fierce, territorial beast emerges from the mist, challenging your presence.", "COMBAT"),
        new GameEvent("A group of hostile bandits demands you surrender your resources.", "COMBAT"),
        new GameEvent("An enemy ambush is sprung from the trees! It's fight or flight.", "COMBAT"),
        new GameEvent("A swarm of giant, biting insects attacks! You must fight them off or flee.", "COMBAT"),
        new GameEvent("You find a crumbling altar covered in glowing, exotic fruit. Choose what to consume.", "ITEM_FIND_FRUITS"),
        new GameEvent("You stumble upon a cache of abandoned provisions, perfectly preserved. Take only what you need.", "ITEM_FIND_FRUITS"),
        new GameEvent("A hidden thicket reveals three different types of valuable herbs.", "ITEM_FIND_FRUITS"),
        new GameEvent("You find a deep, hidden pool of clear water. Do you take time to rest and drink?", "ITEM_FIND_WATER"),
        new GameEvent("A sudden downpour offers a chance to collect rain and rest briefly under a rock overhang.", "ITEM_FIND_WATER"),
        new GameEvent("A rare, ancient artifact—an Ancient Vial—is embedded in a statue. You can take it now.", "ITEM_FIND_SINGLE"),
        new GameEvent("You spot a glowing, unique mushroom growing on a mossy log. It looks potent.", "ITEM_FIND_SINGLE"),
        new GameEvent("You find a large animal carcass. You could scavenge meat for sustenance, but it might be rotten.", "ITEM_FIND_TRAP"),
        new GameEvent("A hunter's snare has caught some fresh game. Taking it is easy, but the hunter might return.", "ITEM_FIND_TRAP"),
        new GameEvent("You reach a gorge blocked by a massive rockslide. You must find a way across.", "SKILL"),
        new GameEvent("A raging river must be crossed. You spot a narrow, unstable log bridge.", "SKILL"),
        new GameEvent("You encounter a confusing, winding cave system that threatens to exhaust you.", "SKILL"),
        new GameEvent("A sudden, severe lightning storm breaks out. You need immediate shelter.", "SKILL")
    ));

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static int getIntInput(int min, int max) {
        String BOLD = "\u001B[1m";
        String red = "\u001B[31m";
        String reset = "\u001B[0m";
        int choice = -1;
        while (choice < min || choice > max) {
            ConsoleHelper.slowPrint("> Enter your choice (" + min + "-" + max + "): ", 0);
            String input = scanner.nextLine();
            try {
                choice = Integer.parseInt(input);
                if (choice < min || choice > max) {
                    ConsoleHelper.slowPrint(BOLD + red + "--- Invalid choice. Please enter a number between " + min + " and " + max + ".\n" + reset, 10);
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.slowPrint(BOLD + red + "--- Invalid input. Please enter a valid number.\n" + reset, 10);
            }
        }
        return choice;
    }

    private static void runItemFindEvent(Hero hero, GameEvent event) {
        switch (event.type) {
            case "ITEM_FIND_FRUITS": runItemFindFruits(hero); break;
            case "ITEM_FIND_WATER": runItemFindWater(hero); break;
            case "ITEM_FIND_SINGLE": runItemFindSingle(hero); break;
            case "ITEM_FIND_TRAP": runItemFindTrap(hero); break;
            default: ConsoleHelper.slowPrint("Unknown resource event type encountered.\n", 10);
        }
    }

    private static void runItemFindFruits(Hero hero) {
        ConsoleHelper.slowPrint("You examine the selection of consumables. Choose wisely:\n", 10);
        for (int i = 0; i < RESOURCE_OPTIONS.length; i++) {
            ConsoleHelper.slowPrint(String.format("   [%d] Consume the %s\n", i + 1, RESOURCE_OPTIONS[i].getItemName()), 10);
        }
        ConsoleHelper.slowPrint("   [4] Ignore all items (Risk minimal, but gain nothing)\n", 10);

        int choice = getIntInput(1, 4);
        if (choice >= 1 && choice <= 3) {
            Rechargeable selectedItem = RESOURCE_OPTIONS[choice - 1];
            ConsoleHelper.slowPrint(String.format("\n--- RESULT: %s ---\n", selectedItem.getItemName().toUpperCase()), 10);
            selectedItem.use(hero); 
        } else {
            ConsoleHelper.slowPrint("You decide the risk isn't worth it and leave the items behind.\n", 10);
            hero.changeEndurance(-5);
        }
    }

    private static void runItemFindWater(Hero hero) {
        String BOLD = "\u001B[1m";
        String purple = "\u001B[35m";
        String reset = "\u001B[0m";
        ConsoleHelper.slowPrint("You contemplate the risks and benefits of taking a break:\n\n", 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [1] Stop and rest (Gain Health, Lose Endurance due to time)\n"+ reset, 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [2] Ignore the rest spot and push on (Risk fatigue, conserve time)\n"+ reset, 10);
        
        int choice = getIntInput(1, 2);
        if (choice == 1) {
            ConsoleHelper.slowPrint("-> You take a moment to rest. The break restores you, but the pause delays you.\n", 10);
            hero.changeHealth(random.nextInt(15) + 15);
            hero.changeEndurance(-random.nextInt(10) - 5);
        } else {
            ConsoleHelper.slowPrint("-> You push on, conserving time but straining your body.\n", 10);
            hero.changeHealth(-5);
            hero.changeEndurance(-10); 
        }
    }

    private static void runItemFindSingle(Hero hero) {
        String BOLD = "\u001B[1m";
        String green = "\u001B[32m";
        String purple = "\u001B[35m";
        String reset = "\u001B[0m";
        AncientVial vial = new AncientVial();
        ConsoleHelper.slowPrint(String.format("You found a powerful item: %s. You can only take one course of action.\n\n", vial.getItemName()), 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [1] Immediately consume the vial (Major boost)\n"+ reset, 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [2] Leave the artifact alone (Safe, no change)\n"+ reset, 10);
          
        int choice = getIntInput(1, 2);
        if (choice == 1) {
            ConsoleHelper.slowPrint(String.format("\n--- RESULT: %s ---\n", vial.getItemName().toUpperCase()), 10);
            vial.use(hero); 
        } else {
            ConsoleHelper.slowPrint(green +"You leave the powerful, but strange, artifact where you found it.\n"+ reset, 10);
        }
    }

    private static void runItemFindTrap(Hero hero) {
        String BOLD = "\u001B[1m";
        String red = "\u001B[31m";
        String purple = "\u001B[35m";
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        ConsoleHelper.slowPrint("You must assess the risk of scavenging this potentially dangerous resource:\n\n", 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [1] Take the resource (High Risk, High Reward)\n"+ reset, 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [2] Leave it alone (Safe, no change)\n"+ reset, 10);
        
        int choice = getIntInput(1, 2);
        if (choice == 1) {
            ConsoleHelper.slowPrint("You quickly attempt to take the resource...\n", 10);
            if (random.nextDouble() > 0.5 - (hero.getStrength() * 0.05)) {
                int healthGain = random.nextInt(20) + 5;
                ConsoleHelper.slowPrint(String.format(BOLD + pink +"-> Success! You gained valuable, fresh resources and restored %d Health.\n"+ reset, healthGain), 10);
                hero.changeHealth(healthGain); 
            } else {
                ConsoleHelper.slowPrint(BOLD + red + "-> Failure. The resource was spoiled or the owner returned! You took heavy damage.\n"+ reset, 10);
                hero.changeHealth(-25);
                hero.changeEndurance(-10); 
            }
        } else {
            ConsoleHelper.slowPrint("You decide the risk is too high and move on.\n", 10);
        }
    }

    private static void runCombatEvent(Hero hero) {
        String BOLD = "\u001B[1m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String purple = "\u001B[35m";
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        String enemy = ENEMIES[random.nextInt(ENEMIES.length)];
        ConsoleHelper.slowPrint(String.format("The event escalates into combat with a fearsome %s!\n\n", enemy), 10);
        ConsoleHelper.slowPrint(BOLD + purple +"   [1] Engage in close combat (Relies on STR)\n"+ reset, 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [2] Attempt a quick evasion (Relies on AGI)\n"+ reset, 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [3] Retreat entirely (Relies on END)\n"+ reset, 10);

        int choice = getIntInput(1, 3);
        switch (choice) {
            case 1: hero.fight(enemy); break;
            case 2:
                ConsoleHelper.slowPrint(green + "You attempt a risky evasion...\n"+ reset, 10);
                if (hero.getAgility() * random.nextDouble() > 4) {
                    ConsoleHelper.slowPrint(pink + "-> Evasion successful! You slipped past the enemy without major harm.\n"+ reset, 10);
                    hero.changeEndurance(-5);
                } else {
                    ConsoleHelper.slowPrint(red + "-> Evasion failed! The enemy struck you during the attempt.\n"+ reset, 10);
                    hero.changeHealth(-20);
                    hero.changeEndurance(-10);
                }
                break;
            case 3:
                ConsoleHelper.slowPrint("You attempt a short retreat to rest...\n", 10);
                hero.changeEndurance(15); 
                hero.changeHealth(-25); 
                break;
        }
    }

    private static void runSkillCheckEvent(Hero hero) {
        String BOLD = "\u001B[1m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String purple = "\u001B[35m";
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        ConsoleHelper.slowPrint("You must decide how to overcome this environmental obstacle:\n\n", 40);
        
        ConsoleHelper.slowPrint(BOLD + purple + "   [1] Brutally force your way through (STR check)\n"+ reset, 40);
        ConsoleHelper.slowPrint(BOLD + purple + "   [2] Carefully navigate the obstacle (AGI check)\n"+ reset, 40);
        ConsoleHelper.slowPrint(BOLD + purple + "   [3] Take a long detour to conserve energy (END check)\n"+ reset, 40);
            
        int choice = getIntInput(1, 3);
        switch (choice) {
            case 1: // Strength Check
                ConsoleHelper.slowPrint(green +"You attempt to brute-force the solution...\n"+ reset, 10);
                if (hero.getStrength() * random.nextDouble() > 6) {
                    ConsoleHelper.slowPrint(BOLD + pink + "-> Success! Your raw power cleared the way, saving time.\n"+ reset, 10);
                    hero.changeEndurance(-15);
                } else {
                    ConsoleHelper.slowPrint(BOLD + red +"-> Failure. The obstacle resisted, and you incurred minor injuries.\n"+ reset, 10);
                    hero.changeEndurance(-25);
                    hero.changeHealth(-10); 
                }
                break;
            case 2: // Agility Check
                ConsoleHelper.slowPrint(green + "You attempt careful, swift navigation...\n"+ reset, 10);
                if (hero.getAgility() * random.nextDouble() > 6) {
                    ConsoleHelper.slowPrint(BOLD + pink +"-> Success! You slipped past easily, barely losing any energy.\n"+ reset, 10);
                    hero.changeEndurance(-5);
                } else {
                    ConsoleHelper.slowPrint(BOLD + red +"-> Failure. You stumbled and took a nasty fall.\n"+ reset, 10);
                    hero.changeEndurance(-15);
                    hero.changeHealth(-20); 
                }
                break;
            case 3: // Endurance Check
                ConsoleHelper.slowPrint(green + "You decide to take the long, safe route...\n"+reset, 10);
                if (hero.getEndurance() > 50) {
                    ConsoleHelper.slowPrint(BOLD + pink +"-> Success. The detour was long but safe. You used your conditioning well.\n"+ reset, 10);
                    hero.changeEndurance(-35); 
                } else {
                    ConsoleHelper.slowPrint(BOLD +red +"-> Failure. The detour was too much. You are severely fatigued.\n"+ reset, 10);
                    hero.changeEndurance(-45);
                    hero.changeHealth(-5); 
                }
                break;
        }
    }

    private static void runDay(Hero hero, GameEvent event) {
        String BOLD = "\u001B[1m";
        String orange = "\u001B[38;2;255;165;0m";
        String white = "\u001B[37m";
        String reset = "\u001B[0m";
        ConsoleHelper.slowPrint(String.format(BOLD + orange + "----------------------------------------------------------------------------------------------------------" + reset), 10);
        ConsoleHelper.slowPrint(String.format(BOLD + orange + "\n--- CHALLENGE" + white + ": %s \n" + reset, event.description), 10);
        ConsoleHelper.slowPrint(String.format(BOLD + orange + "----------------------------------------------------------------------------------------------------------\n" + reset), 10);
        
        if (event.type.startsWith("ITEM_FIND")) {
            runItemFindEvent(hero, event);
        } else {
            switch (event.type) {
                case "COMBAT": runCombatEvent(hero); break;
                case "SKILL": runSkillCheckEvent(hero); break;
            }
        }
    }
    
    public static void main(String[] args) {
        String BOLD = "\u001B[1m";
        String navy = "\u001B[38;2;0;0;128m";
        String dblue = "\u001B[38;2;0;0;90m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String blue = "\u001B[34m";
        String purple = "\u001B[35m";
        String pink = "\u001B[38;2;255;182;193m"; 
        String reset = "\u001B[0m";
        
        ConsoleHelper.slowPrint(BOLD + blue + "==========" + navy + "==========" + dblue + "==========" + navy + "==========" + blue + "==========\n\n" + reset, 10);
        ConsoleHelper.slowPrint(BOLD + pink + "                HUNGER GAMES                \n\n" + reset, 40);
        ConsoleHelper.slowPrint(BOLD + blue + "==========" + navy + "==========" + dblue + "==========" + navy + "==========" + blue + "==========\n\n" + reset, 10);

        System.out.print("Please enter your hero's name: ");
        String userName = scanner.nextLine();

        ConsoleHelper.slowPrint("\nHello, " + userName + "! Choose Your Hero Class:\n", 10);
        ConsoleHelper.slowPrint(BOLD + purple + "   [1] Warrior: (Strong, Durable | High STR (9), Low AGI (5), High HP (100))\n", 10);
        ConsoleHelper.slowPrint("   [2] Ranger:  (Balanced, Agile | Balanced STR/AGI (6/8), Good END (90))\n", 10);
        ConsoleHelper.slowPrint("   [3] Scout:   (Evasion Focus | Low STR (4), High AGI (9), High END (100))\n" + reset, 10);

        int heroChoice = getIntInput(1, 3);
        Hero playerHero;
        
        switch (heroChoice) {
            case 1: playerHero = new Warrior(userName); break;
            case 2: playerHero = new Ranger(userName); break;
            case 3: playerHero = new Scout(userName); break;
            default: playerHero = new Warrior(userName);
        }

        ConsoleHelper.slowPrint(String.format("\n%s, the %s, begins the Adventure! \n", 
            playerHero.getName(), playerHero.getClass().getSimpleName()), 10);

        final int TOTAL_DAYS = 10;
        boolean survived = true;

        for (int day = 1; day <= TOTAL_DAYS && survived; day++) {
            ConsoleHelper.slowPrint(String.format(BOLD + blue +"\n================ DAY %d: NEW CHALLENGE =================\n"+ reset, day), 10);
            ConsoleHelper.slowPrint("\t\t   <<< STATUS >>>\t\n", 10);
            ConsoleHelper.slowPrint(playerHero.getStatusString(), 10);

            GameEvent currentEvent = CHALLENGE_EVENTS.get(random.nextInt(CHALLENGE_EVENTS.size()));
            runDay(playerHero, currentEvent);
            
            playerHero.changeEndurance(-5);
            
            ConsoleHelper.slowPrint("\n--- End of Day Summary ---\n", 10);
            ConsoleHelper.slowPrint(playerHero.getStatusString(), 10);

            if (playerHero.getHealth() <= 0 || playerHero.getEndurance() <= 0) {
                ConsoleHelper.slowPrint(String.format(red + "\n*** GAME OVER on Day %d! ***\n"+ reset, day), 10);
                ConsoleHelper.slowPrint(red + playerHero.getName() + " collapsed from exhaustion or injury.\n"+ reset, 10);
                survived = false;
            }
        }

        if (survived) {
            ConsoleHelper.slowPrint("\n*************************************************\n", 10);
            ConsoleHelper.slowPrint(String.format(green + "* VICTORY! %s survived all %d days! ***\n"+ reset, playerHero.getName(), TOTAL_DAYS), 10);
            ConsoleHelper.slowPrint("*************************************************\n", 10);
        }
        scanner.close();
    }
}
