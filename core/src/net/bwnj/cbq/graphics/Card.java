package net.bwnj.cbq.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import net.bwnj.cardbattle.Engine.CardArchitype;

public class Card extends Image {
    public CardArchitype architype;
    ShapeRenderer sr;

    public Card(CardArchitype cardArchitype) {
        this.architype = cardArchitype;
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        super.setWidth(height * architype.card_ratio);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        setBounds(getX(), getY(), getWidth(), getHeight());
        System.out.println("Set bounds to " +getX() + " " + getY() + " " + getWidth() + " " + getHeight());

        try {
            batch.draw(architype.frameTexture, getX(), getY(), getHeight() * architype.card_ratio, getHeight());
            batch.end();

            sr.setProjectionMatrix(batch.getProjectionMatrix());
            sr.setColor(0,0,0, parentAlpha);

            sr.begin();
            sr.setColor(Color.BLACK);
            sr.rect(getX() + architype.card_art_x * getHeight() ,getY() + architype.card_art_y * getHeight(), architype.card_art_width * getHeight(), architype.card_art_height * getHeight());
//            sr.rect(getX()  ,getY() , getWidth(), getHeight());
            sr.end();

            batch.begin();
            batch.draw(architype.art, getX() + architype.card_art_x * getHeight(), getY() + architype.card_art_y * getHeight(), architype.card_art_width * getHeight(), architype.card_art_height * getHeight());

            BitmapFont bodyFont = FontCache.getCardFonts(getHeight()).get("bodyFont");
            BitmapFont nameFont = FontCache.getCardFonts(getHeight()).get("nameFont");

            bodyFont.draw(batch, "Power: " + architype.power, getX() + architype.card_text_x * getHeight(), getY() + architype.card_text_y * getHeight());
            bodyFont.draw(batch, "Health: " + architype.toughness, getX() + architype.card_text_x * getHeight(), getY() + architype.card_text_y * getHeight() - bodyFont.getData().capHeight * 1.5F );
            nameFont.draw(batch, architype.name, getX() + architype.card_name_x * getHeight(), getY() + architype.card_name_y * getHeight());


        } catch (Exception e) {
            System.out.println(e);
            System.out.println(e.getStackTrace());
        } finally {
            sr.setColor(Color.WHITE);
        }

        super.draw(batch, parentAlpha);
    }

    public String toString() {
        return architype.toString();
    }

}
