package net.bwnj.cardbattle.Engine;

import net.bwnj.cbq.graphics.Card;

import java.util.List;

public class CardBattleGame {
    public List<Location> Locations;

    public CardBattleGame(List<Location> locations) {
        this.Locations = locations;
    }

    public Location getLocationByName(String name){
        for (Location loc : this.Locations) {
            if (loc.Name.equals(name)) {
                return loc;
            }
        }
        return null;
    }

    public Location get(String name) {
        return this.getLocationByName(name);
    }

    /***
     * Moves a card from the top of the pile in one location to the
     * top of the pile in another locaiton.
     *
     * @param count number of cards to move
     * @param sourceLocation Source location of the card
     * @param destLocation Dest location of the card
     * @return Number of cards moved
     */
    public Integer moveCards(int count, String sourceLocation, String destLocation){

        if (count > get(sourceLocation).Cards.size()) {
            return null;
        }

        int i = 0;
        for (; i< count; i++) {
            Pile source = get(sourceLocation).Cards;
            Pile dest = get(destLocation).Cards;
            Card card = source.remove(0);
            dest.add(0, card);
        }
        return i;
    }

    public boolean moveSpecificCard(int sourcePosition, String sourceLocation, String destLocation) {
        if (sourcePosition >= get(sourceLocation).Cards.size()) {
            return false;
        }
        Card card = get(sourceLocation).Cards.remove(sourcePosition);
        get(destLocation).Cards.add(0,card);

        return true;
    }
}

