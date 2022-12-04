package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Card {

    public float x, y;

    public CardArchitype Architype;
    public String Format = "$n [ $p/$t ]\n";

    public Card(CardArchitype cardArchitype) {
        this.Architype = cardArchitype;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
            String cardstring = new String(Format);
            cardstring = cardstring.replace("$n", Architype.name);
            cardstring = cardstring.replace("$s", defaultStr(Architype.suit, ""));
            cardstring = cardstring.replace("$c", defaultStr(Architype.cost, ""));
            cardstring = cardstring.replace("$p", defaultStr(Architype.power, ""));
            cardstring = cardstring.replace("$t", defaultStr(Architype.toughness, ""));
            sb.append(cardstring);
        return sb.toString();
    }
    String defaultStr(Object o, String def) {
        if (o == null) {
            return def;
        }
        return o.toString();
    }


    public void render(SpriteBatch batch, ShapeRenderer sr, BitmapFont nameFont, BitmapFont bodyFont, float x, float y, float height) {


        try {
            batch.draw(Architype.frameTexture, x,y, height * Architype.card_ratio, height);
            batch.draw(Architype.art, x + Architype.card_art_x * height ,y + Architype.card_art_y * height, Architype.card_art_width * height, Architype.card_art_height * height);


            sr.setColor(Color.BLACK);
            sr.rect(x + Architype.card_art_x * height ,y + Architype.card_art_y * height, Architype.card_art_width * height, Architype.card_art_height * height);

            bodyFont.draw(batch, "Power: " + Architype.power, x + Architype.card_text_x * height, y + Architype.card_text_y * height);
            bodyFont.draw(batch, "Health: " + Architype.toughness, x + Architype.card_text_x * height, y + Architype.card_text_y * height - bodyFont.getData().capHeight * 1.5F );
            nameFont.draw(batch, Architype.name, x + Architype.card_name_x * height, y + Architype.card_name_y * height);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
