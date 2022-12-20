package net.bwnj.cbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.bwnj.cbq.CardBattleQuest;
import net.bwnj.cbq.InputListener;
import net.bwnj.cbq.graphics.Card;

// assets?
// https://www.artstation.com/artwork/o4Yn4
// https://graphicriver.net/item/creature-cards/20788252
// https://www.artstation.com/artwork/o4Yn4


public class GameScreen implements Screen, InputListener {

    private final CardBattleQuest cbq;
    private Stage stage;

    public GameScreen(CardBattleQuest _cbq) {

        cbq = _cbq;
        this.stage = new Stage(new FitViewport(cbq.WORLD_WIDTH, cbq.WORLD_HEIGHT, cbq.camera));
        float w = stage.getWidth();

    }


    public void initBattle() {
        cbq.cardBattleGame.get(CardBattleQuest.MONSTER_DECK).Cards.shuffle();
        cbq.cardBattleGame.get(CardBattleQuest.PLAYER_DECK).Cards.shuffle();
        cbq.cardBattleGame.moveCards(4, CardBattleQuest.PLAYER_DECK, CardBattleQuest.PLAYER_HAND);
        layoutPlayerHand();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void processClick(Object o) {

    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        System.out.println("touch at " + x + " " + y);
        return false;
    }

    @Override
    public void show() {
    }


    public void layoutPlayerHand() {
        float drawpos_x = 20;
        float drawpos_y = 20;
        float i = 0;
        for (Card card : cbq.cardBattleGame.get(CardBattleQuest.PLAYER_HAND).Cards) {
            card.setPosition(drawpos_x + (.25F * i * stage.getWidth()), drawpos_y);
            card.setHeight(stage.getHeight() * .1F);
            stage.addActor(card);
            i++;
        }
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
    }
}
