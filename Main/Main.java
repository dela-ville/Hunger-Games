package FinancialHungerGame;

import FinancialHungerGame.character.Hero;
import FinancialHungerGame.character.classes.Ranger;
import FinancialHungerGame.character.classes.Scout;
import FinancialHungerGame.character.classes.Warrior;
import FinancialHungerGame.game.GameEvent;
import FinancialHungerGame.game.LogicHub;
import FinancialHungerGame.utility.DisplayHub;
import FinancialHungerGame.utility.InputScanner;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
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

    public static void main(String[] args) {
        final int TOTAL_DAYS = 10;
        boolean survived = true;
        Hero playerHero = null; 
        
        for (int day = 1; day <= TOTAL_DAYS && survived; day++) {
            GameEvent currentEvent = CHALLENGE_EVENTS.get(DisplayHub.getRandom().nextInt(CHALLENGE_EVENTS.size()));
            
            LogicHub.runDay(playerHero, currentEvent); 
            
        }

        InputScanner.getScanner().close(); 
    }
}
