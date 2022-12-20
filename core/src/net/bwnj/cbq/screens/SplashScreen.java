package net.bwnj.cbq.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.bwnj.cardbattle.Engine.CardArchitype;
import net.bwnj.cbq.CardBattleQuest;
import net.bwnj.cbq.graphics.Card;

public class SplashScreen implements Screen, InputProcessor {

    private final CardBattleQuest cbq;
    private Stage stage;

    private Image splashImg;

    private Image i;

    private int clicks = 0;

    public SplashScreen(final CardBattleQuest _cbq) {
        cbq = _cbq;
        this.stage = new Stage(new FitViewport(cbq.WORLD_WIDTH, cbq.WORLD_HEIGHT, cbq.camera));
        float w = stage.getWidth();
    }

    @Override
    public void show() {

        try {
            CardArchitype ca = new CardArchitype("owl", "monster", 4, 5, 6);
            Card card = new Card(ca);

            card.setHeight(200);
            card.setPosition(stage.getWidth(), stage.getHeight());
            card.addAction(
                    moveTo(stage.getWidth() / 2 - (card.getWidth() / 2), stage.getHeight() / 2 - (card.getHeight() / 2), 2f, Interpolation.bounceOut)
            );

            card.addListener(new ClickListener()
            {
                @Override
                public void touchUp  (InputEvent event, float x, float y, int pointer, int button) {
                    if (clicks == 0) {
                        stage.clear();
                        clicks = 1;
                    } else {
                        cbq.startNewGame();
                    }
                }

            });
            stage.addActor(card);

            Gdx.input.setInputProcessor(stage);

        } catch (Exception e) {
            System.out.println(e);
        }



    }

    @Override
    public void render(float delta) {
        try {
            Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            update(delta);

            stage.draw();
            if (clicks > 0) {
                if (clicks > 1) {
                    cbq.startNewGame();
                } else {
                    clicks++;
                    System.out.println(clicks);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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
        stage.clear();
        for (Actor a : stage.getActors()) {
            System.out.println(a);
        }
        stage.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("got touch at " + screenX + " " + screenY);
//        this.cbq.startNewGame();
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
