package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.graphics.Color;
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
            cardstring = cardstring.replace("$n", Architype.Name);
            cardstring = cardstring.replace("$s", defaultStr(Architype.Suit, ""));
            cardstring = cardstring.replace("$c", defaultStr(Architype.Cost, ""));
            cardstring = cardstring.replace("$p", defaultStr(Architype.Power, ""));
            cardstring = cardstring.replace("$t", defaultStr(Architype.Toughness, ""));
            sb.append(cardstring);
        return sb.toString();
    }
    String defaultStr(Object o, String def) {
        if (o == null) {
            return def;
        }
        return o.toString();
    }


    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch, ShapeRenderer sr) {
        batch.draw(Architype.Texture, x, y, Architype.draw_width, Architype.draw_height);

        sr.setColor(Color.BLACK);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x,y,Architype.draw_width, Architype.draw_height);

    }

}
