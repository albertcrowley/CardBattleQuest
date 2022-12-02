package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CardArchitype {
    public String Name;
    public Integer Cost;
    public Integer Power;
    public Integer Toughness;
    public String Suit = "";
    public TextureRegion Texture;
    public float draw_width = 15F;
    public float draw_height = 15F;

    public float card_frame_widht = 969F;
    public float card_frame_height = 1352F;
    public float card_ratio = card_frame_widht / card_frame_height;
    public float card_art_x = 82F/card_frame_height;
    public float card_art_y = 602F/ card_frame_height;
    public float card_art_width = (890-82) / card_frame_height;
    public float card_art_height = (1196-602) / card_frame_height;
    public float card_text_x = 200F/card_frame_height;
    public float card_text_y = 425F/card_frame_height;
    public float card_name_x = .07F ;
    public float card_name_y = .935F ;
    public com.badlogic.gdx.graphics.Texture frameTexture;

    void initGraphics() {
        frameTexture = new Texture("card-frame.png");
    }

    public CardArchitype(String name) {
        this.Name = name;
        this.Cost = 0;
        initGraphics();
    }
    public CardArchitype(String name, String suit, int cost, int power, int toughness) {
        Name = name;
        Suit = suit;
        Cost = cost;
        Power = power;
        Toughness = toughness;
        initGraphics();
    }

    public CardArchitype(String name, TextureRegion texture, String suit, int cost, int power, int toughness) {
        Name = name;
        Texture = texture;
        Suit = suit;
        Cost = cost;
        Power = power;
        Toughness = toughness;
        initGraphics();
    }

    public String toString() {
//        return "%s [ (%d) %d/%d ]".formatted(Name, Cost, Power, Toughness);   // Name + " [" + Cost + "]";
        return String.join(" ", new String[] {Name, String.valueOf(Cost), Power.toString(), Toughness.toString()});
    }



}
