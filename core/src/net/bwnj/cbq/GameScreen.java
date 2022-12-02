package net.bwnj.cbq;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.bwnj.cardbattle.Engine.Card;
import net.bwnj.cardbattle.Engine.CardArchitype;

// assets?
// https://www.artstation.com/artwork/o4Yn4
// https://graphicriver.net/item/creature-cards/20788252
// https://www.artstation.com/artwork/o4Yn4


class GameScreen implements Screen {

    private Camera camera;
    private Viewport viewport;

    private SpriteBatch batch;
    private Texture background;

    private float backgroundOffest;
    private final int WORLD_WIDTH = 720;
    private final int WORLD_HEIGHT = 1280;

    private TextureAtlas textureAtlas;
    private TextureRegion bot, shark, squirel;

    float card_width = 5.75F * 2F;
    float card_height = 7.5F * 2F;

    Card card;

    GameScreen() {
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

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ShapeRenderer sr = new ShapeRenderer();
        try {
            sr.setProjectionMatrix(this.viewport.getCamera().combined);

            batch.begin();
            backgroundOffest += delta;
            if (backgroundOffest % WORLD_HEIGHT == 0) {
                backgroundOffest = 0;
            }
            batch.draw(background, 0,-backgroundOffest, WORLD_WIDTH, WORLD_HEIGHT);
            batch.draw(background, 0,-backgroundOffest+WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);


            // draw a card
            card.x = 10;
            card.y = 50;
            card.render(batch, sr, 300,600, 200);


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
