package Main.character;

import Main.utility.DisplayHub;

public abstract class Hero {
    private String name;
    private int health; 
    private int endurance; 
    private final int strength; 
    private final int agility;  

    public Hero(String name, int health, int endurance, int strength, int agility) {
        this.name = name;
        this.health = health;
        this.endurance = endurance;
        this.strength = strength;
        this.agility = agility;
    }

    public abstract void fight(String enemy);

    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getEndurance() { return endurance; }
    public int getStrength() { return strength; }
    public int getAgility() { return agility; }

    public void changeHealth(int amount) {
        this.health = Math.min(100, Math.max(0, this.health + amount));
    }
    
    public void changeEndurance(int amount) {
        this.endurance = Math.min(100, Math.max(0, this.endurance + amount));
    }
  
    public String getStatusString() {
        // Reference colors from DisplayHub
        String green = DisplayHub.green;
        String reset = DisplayHub.reset;
      
        return String.format(green + "  > Health: %d/100 | Endurance: %d/100\n  > STR: %d | AGI: %d\n" + reset, 
            this.health, this.endurance, this.strength, this.agility);
    }
}
