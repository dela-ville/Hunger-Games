package FinancialHungerGame.item;

import FinancialHungerGame.character.Hero;

public interface Rechargeable {
    void use(Hero hero);
    String getItemName();
}
