package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CardArchitype {
    public String Name;
    public Integer Cost;
    public Integer Power;
    public Integer Toughness;
    public String Suit = "";
    public TextureRegion Texture;
    public float draw_width = 5.75F * 4;
    public float draw_height = 7.5F * 4;

    public CardArchitype(String name) {
        this.Name = name;
        this.Cost = 0;
    }
    public CardArchitype(String name, String suit, int cost, int power, int toughness) {
        Name = name;
        Suit = suit;
        Cost = cost;
        Power = power;
        Toughness = toughness;
    }

    public CardArchitype(String name, TextureRegion texture, String suit, int cost, int power, int toughness) {
        Name = name;
        Texture = texture;
        Suit = suit;
        Cost = cost;
        Power = power;
        Toughness = toughness;
    }

    public String toString() {
//        return "%s [ (%d) %d/%d ]".formatted(Name, Cost, Power, Toughness);   // Name + " [" + Cost + "]";
        return String.join(" ", new String[] {Name, String.valueOf(Cost), Power.toString(), Toughness.toString()});
    }



}
