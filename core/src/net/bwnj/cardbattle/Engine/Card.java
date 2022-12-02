package net.bwnj.cardbattle.Engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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


    public void render(com.badlogic.gdx.graphics.g2d.SpriteBatch batch, ShapeRenderer sr, float x, float y, float height) {
        batch.draw(Architype.frameTexture, x,y, height * Architype.card_ratio, height);
        batch.draw(Architype.Texture, x + Architype.card_art_x * height ,y + Architype.card_art_y * height, Architype.card_art_width * height, Architype.card_art_height * height);


        sr.setColor(Color.BLACK);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(x + Architype.card_art_x * height ,y + Architype.card_art_y * height, Architype.card_art_width * height, Architype.card_art_height * height);

        FreeTypeFontGenerator.setMaxTextureSize(2048);
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("DroidSerif-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = (int) (height/12F);
//        fontParameter.borderWidth = height/200F ;
        fontParameter.color = new Color(.1F,.1F,.1F,1);
        fontParameter.borderColor = Color.GRAY;

        BitmapFont bodyfont = fontGenerator.generateFont(fontParameter);
        fontParameter.size = (int) (height/24F);
        BitmapFont namefont = fontGenerator.generateFont(fontParameter);

        bodyfont.draw(batch, "Power: " + Architype.Power, x + Architype.card_text_x * height, y + Architype.card_text_y * height);
        bodyfont.draw(batch, "Health:  " + Architype.Toughness, x + Architype.card_text_x * height, y + Architype.card_text_y * height - bodyfont.getData().capHeight * 1.5F );
        namefont.draw(batch, Architype.Name, x + Architype.card_name_x * height, y + Architype.card_name_y * height);

        fontGenerator.dispose();


    }

}
