package game.items;

import game.characters.Hero;

public interface Rechargeable {
    void use(Hero hero);
    String getItemName();
}
