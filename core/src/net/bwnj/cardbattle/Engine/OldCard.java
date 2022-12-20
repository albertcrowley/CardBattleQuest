package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.math.Rectangle;

public class OldCard {

    public float x, y, move_to_x, move_to_y, dx=0, dy=0, max_speed=50, min_speed = 5, accel=10;
    public float height, width;
    public boolean onScreen = false;
    public boolean moving = false;


    public CardArchitype Architype;
    public String Format = "$n [ $p/$t ]\n";

    public OldCard(CardArchitype cardArchitype) {
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

    public void setPos(float _x, float _y, float _height) {
        x = move_to_x = _x;
        y = move_to_y = _y;

        height = _height;
        width = height * Architype.card_ratio;
        onScreen = true;
    }

    public Rectangle getBoundingRectangle() {
        if (!onScreen) { return null; }

        return new Rectangle(x,y,width,height);
    }

//    public void move(float delta) {
//        if (move_to_y > y) { dy = dy + accel * delta; }
//        if (move_to_y < y) { dy = dy - accel * delta; }
//        if (move_to_x > x) { dx = dx + accel * delta; }
//        if (move_to_x < x) { dx = dx - accel * delta; }
//        x = x + dx;
//        y = y + dy;
//    }
//
//    public void done_move() {
//        moving = false;
//        x = move_to_x;
//        y = move_to_y;
//        dx = 0;
//        dy = 0;
//    }
//
//    public void render(SpriteBatch batch, ShapeRenderer sr, BitmapFont nameFont, BitmapFont bodyFont, float delta) {
//        try {
//            move(delta);
//            batch.draw(Architype.frameTexture, x,y, height * Architype.card_ratio, height);
//            batch.draw(Architype.art, x + Architype.card_art_x * height ,y + Architype.card_art_y * height, Architype.card_art_width * height, Architype.card_art_height * height);
//
//
//            sr.setColor(Color.BLACK);
//            sr.rect(x + Architype.card_art_x * height ,y + Architype.card_art_y * height, Architype.card_art_width * height, Architype.card_art_height * height);
//
//            bodyFont.draw(batch, "Power: " + Architype.power, x + Architype.card_text_x * height, y + Architype.card_text_y * height);
//            bodyFont.draw(batch, "Health: " + Architype.toughness, x + Architype.card_text_x * height, y + Architype.card_text_y * height - bodyFont.getData().capHeight * 1.5F );
//            nameFont.draw(batch, Architype.name, x + Architype.card_name_x * height, y + Architype.card_name_y * height);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
