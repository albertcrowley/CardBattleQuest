package net.bwnj.cardbattle.Engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pile extends ArrayList<Card> {
    public String Name;
    public String Format = "$i $n [ $p/$t ]\n";

    public Pile() {
        this.clear();
    }

    public Pile(List<Card> cards) {
        this.clear();
        this.addAll(cards);
    }

    /***
     *
     * Format string uses the following substitutions:
     *   $i card number in pile
     *   $n name
     *   $s suit
     *   $c cost
     *   $p power
     *   $t toughness
     *
     * @param format Format string
     */
    public void setFormat(String format) {
        Format = format;
    }

    public Pile(List<Card> cards, String name) {
        this.Name = name;
        this.clear();
        this.addAll(cards);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < this.size(); i++) {
            Card c = this.get(i);
            c.Format = this.Format.replace("$i", String.valueOf(i+1));
            sb.append(c);
        }
        return sb.toString();
    }


    public void shuffle() {
        Collections.shuffle(this);
    }

}
