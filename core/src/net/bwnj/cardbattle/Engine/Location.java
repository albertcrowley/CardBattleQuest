package net.bwnj.cardbattle.Engine;


public class Location {
    public String Name;
    public Pile Cards;
    public String Format = "$n [ $p/$t ]\n";


    public Location(String name) {
        this.Name = name;
        this.Cards = new Pile();
    }

    public Location(String name, Pile deck) {
        this.Name = name;
        this.Cards = deck;
    }


    public Pile getCards() {
        return Cards;
    }

    public void setCards(Pile cards) {
        Cards = cards;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Location: %s\n".formatted(Name));
        sb.append(this.Cards.toString());
        return sb.toString();
    }


}
