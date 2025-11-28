package game;

public class GameEvent {
    public final String description;
    // Types: COMBAT, ITEM_FIND_FRUITS, ITEM_FIND_SINGLE, ITEM_FIND_WATER, ITEM_FIND_TRAP, or SKILL
    public final String type; 
    
    public GameEvent(String description, String type) {
        this.description = description;
        this.type = type;
    }
}
