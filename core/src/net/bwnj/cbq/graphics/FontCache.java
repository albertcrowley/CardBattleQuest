package net.bwnj.cbq.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;
import java.util.Map;

public class FontCache {

    private static Map<Float, Map<String, BitmapFont>> fontCache = new HashMap<Float, Map<String, BitmapFont>>();


    public static Map<String, BitmapFont> getCardFonts(Float card_height) {

        if (fontCache.containsKey(card_height)) {
            return fontCache.get(card_height);
        }

        FreeTypeFontGenerator fontGenerator = null;
        FreeTypeFontGenerator.setMaxTextureSize(2048);
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("DroidSerif-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = (int) (card_height/12F);
        fontParameter.color = new Color(.1F,.1F,.1F,1);
        fontParameter.borderColor = Color.GRAY;

        BitmapFont bodyfont = fontGenerator.generateFont(fontParameter);
        fontParameter.size = (int) (card_height/24F);
        BitmapFont namefont = fontGenerator.generateFont(fontParameter);

        Map<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
        fonts.put("bodyFont", bodyfont);
        fonts.put("nameFont", namefont);

        fontCache.put(card_height, fonts);

        return fonts;
    }

}
