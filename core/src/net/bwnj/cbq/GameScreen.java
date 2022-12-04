package net.bwnj.cbq;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.bwnj.cardbattle.Engine.Card;
import net.bwnj.cardbattle.Engine.CardArchitype;
import net.bwnj.cardbattle.Engine.Game;
import net.bwnj.cardbattle.Engine.Location;

import java.util.HashMap;
import java.util.Map;

// assets?
// https://www.artstation.com/artwork/o4Yn4
// https://graphicriver.net/item/creature-cards/20788252
// https://www.artstation.com/artwork/o4Yn4


class GameScreen implements Screen {

    public static Camera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private Texture background;

    private float backgroundOffest;
    private final int WORLD_WIDTH = 720;
    private final int WORLD_HEIGHT = 1280;

    private TextureAtlas textureAtlas;
    private TextureRegion bot, shark, squirel;

    float card_width = 115F;
    float card_height = 150F;

    Game game = null;

    Map<Float, Map<String, BitmapFont>> fontCache = new HashMap<Float, Map<String, BitmapFont>>();

    Card card;

    GameScreen(Game _game) {
        game = _game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("darkPurpleStarscape.png");
        backgroundOffest = 0;

        // setup texture atlas
        textureAtlas = new TextureAtlas("packs/diff_robots.atlas");
        bot = textureAtlas.findRegion("owl");


        batch = new SpriteBatch();

        CardArchitype ca = new CardArchitype("owl", bot, "monster",1,2,3 );
        card = new Card(ca);

    }

    public void initBattle() {
        Location l = game.get("monsterDeck");
        game.get("monsterDeck").Cards.shuffle();
        game.get("playerDeck").Cards.shuffle();

        game.moveCards(4, "playerDeck", "playerHand");
    }

    @Override
    public void show() {
    }

    Map<String, BitmapFont> getCardFonts(Float card_height) {

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

    @Override
    public void render(float delta) {
        System.out.println("delta " + delta + " approx fps " + 1/delta );
        ShapeRenderer sr = new ShapeRenderer();
        try {
            Map<String, BitmapFont> fonts = getCardFonts(card_height);

            sr.setProjectionMatrix(this.viewport.getCamera().combined);
            sr.begin(ShapeRenderer.ShapeType.Line);


            batch.begin();
            backgroundOffest += delta;
            if (backgroundOffest % WORLD_HEIGHT == 0) {
                backgroundOffest = 0;
            }
            batch.draw(background, 0,-backgroundOffest, WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(background, 0,-backgroundOffest+WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);


            // draw a player hand

            float hand_x = 20;
            float hand_y = 20;
            float i = 0;
            for (Card card : game.get("playerHand").Cards) {
//                System.out.println(card.Architype.name);
                card.render(batch, sr, fonts.get("nameFont"), fonts.get("bodyFont"), hand_x + (1.5F * i * card_width), hand_y, card_height);
                i++;
            }

            // draw a card
            card.x = 10;
            card.y = 50;
            card.render(batch, sr, fonts.get("nameFont"), fonts.get("bodyFont"), 300,600, 200);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            batch.end();
            sr.end();
        }


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
