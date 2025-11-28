package Main.game;

import Main.character.Hero;
import Main.item.Rechargeable;
import Main.utility.DisplayHub;
import Main.utility.InputScanner;

import java.util.Random;

public class LogicHub {
    
    private static final Random random = DisplayHub.getRandom();
    
    private static final Rechargeable[] RESOURCE_OPTIONS = {
        // ... (Item instantiations) ...
    };
    
    private static final String[] ENEMIES = {
        // ... (Enemy array) ...
    };
    
    public static void runDay(Hero hero, GameEvent event) {
        DisplayHub.slowPrint(String.format(DisplayHub.BOLD + DisplayHub.orange + "----------------------------------------------------------------------------------------------------------" + DisplayHub.reset), 10);
        DisplayHub.slowPrint(String.format(DisplayHub.BOLD + DisplayHub.orange + "\n--- CHALLENGE" + DisplayHub.white + ": %s \n" + DisplayHub.reset, event.description), 10);
        DisplayHub.slowPrint(String.format(DisplayHub.BOLD + DisplayHub.orange + "----------------------------------------------------------------------------------------------------------\n" + DisplayHub.reset), 10);

        if (event.type.startsWith("ITEM_FIND")) {
            // ...
        } else {
            // ...
        }
    }
}
