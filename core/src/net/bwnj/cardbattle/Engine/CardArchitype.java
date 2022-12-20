package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class CardArchitype {
    public String name = "";
    public Integer cost = 0;
    public Integer power = 0;
    public Integer toughness = 0;
    public String suit = "";
    public TextureRegion art;
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
        if (art == null) {
            art = new TextureRegion(new Texture("default-art.png"));
        }
    }

    public CardArchitype(String _name) {
        this.name = _name;
        this.cost = 0;
        initGraphics();
    }
    public CardArchitype(String _name, String _suit, int _cost, int _power, int _toughness) {
        name = _name;
        suit = _suit;
        cost = _cost;
        power = _power;
        toughness = _toughness;
        initGraphics();
    }

    public CardArchitype(String _name, TextureRegion _art, String _suit, int _cost, int _power, int _toughness) {
        name = _name;
        this.art = _art;
        suit = _suit;
        cost = _cost;
        power = _power;
        toughness = _toughness;
        initGraphics();
    }

    public String toString() {
//        return "%s [ (%d) %d/%d ]".formatted(Name, Cost, Power, Toughness);   // Name + " [" + Cost + "]";
        return String.join(" ", new String[] {name, String.valueOf(cost), power.toString(), toughness.toString()});
    }



}
