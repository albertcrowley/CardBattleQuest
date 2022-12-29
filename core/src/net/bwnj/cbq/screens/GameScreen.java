package net.bwnj.cbq.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import net.bwnj.cardbattle.Engine.Location;
import net.bwnj.cardbattle.Engine.Pile;
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
    SpriteBatch batch = new SpriteBatch();
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    Location playerHand, playerField, monsterHand, mosterField, playerDeck, monsterDeck;


    public GameScreen(CardBattleQuest _cbq) {

        cbq = _cbq;
        this.stage = new Stage(new FitViewport(cbq.WORLD_WIDTH, cbq.WORLD_HEIGHT, cbq.camera));
        float w = stage.getWidth();

        playerHand = cbq.cardBattleGame.getLocationByName(CardBattleQuest.PLAYER_HAND);
        playerField = cbq.cardBattleGame.getLocationByName(CardBattleQuest.PLAYER_FIELD);
        monsterHand = cbq.cardBattleGame.getLocationByName(CardBattleQuest.MONSTER_HAND);
        mosterField = cbq.cardBattleGame.getLocationByName(CardBattleQuest.MONSTER_FIELD);
        playerDeck = cbq.cardBattleGame.getLocationByName(CardBattleQuest.PLAYER_DECK);
        monsterDeck  = cbq.cardBattleGame.getLocationByName(CardBattleQuest.MONSTER_DECK);
    }


    public void initBattle() {
        cbq.cardBattleGame.get(CardBattleQuest.MONSTER_DECK).Cards.shuffle();
        cbq.cardBattleGame.get(CardBattleQuest.PLAYER_DECK).Cards.shuffle();
        cbq.cardBattleGame.moveCards(5, CardBattleQuest.PLAYER_DECK, CardBattleQuest.PLAYER_HAND);
        layoutCards();
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


    public void layoutCards() {
        float drawpos_x = 20;
        float drawpos_y = 20;
        float i = 0;
        float gutter = 0;
        Pile playerHandCards = playerHand.Cards;
        if (playerHandCards.size() > 0) {
            Card c1 = playerHandCards.get(0);
            c1.setHeight(stage.getHeight() * .1F);
            gutter = 1.0F / (playerHandCards.size() + 1) * (stage.getWidth() - playerHandCards.size() * c1.getWidth());
            for (Card card : playerHandCards) {
                card.setHeight(stage.getHeight() * .1F);
                card.setPosition(gutter * (i + 1) + card.getWidth() * i, drawpos_y);
                addCardToStage(card);
                i++;
            }
        }

        Pile playerFieldCards = playerField.Cards;
        if (playerFieldCards.size() > 0 ) {
            drawpos_x = 20;
            i = 0;
            Card c1 = playerFieldCards.get(0);
            c1.setHeight(stage.getHeight() * .1F);
            gutter = 1.0F / (playerFieldCards.size() + 1) * (stage.getWidth() - playerFieldCards.size() * c1.getWidth());

            for (Card card : playerFieldCards) {
                card.setHeight(stage.getHeight() * .1F);
                drawpos_y = 20 * 3 + card.getHeight();
                card.setPosition(gutter * (i + 1) + card.getWidth() * i, drawpos_y);
                addCardToStage(card);
                i++;
            }
        }

    }

    public void addCardToStage(Card card) {
        GameScreen gs = this;
        if (!stage.getActors().contains(card,true)) {
            System.out.println("Adding " + card.toString() + " to the stage");
            stage.addActor(card);
            card.addListener(new ClickListener()
            {
                @Override
                public void touchUp  (InputEvent event, float x, float y, int pointer, int button) {
                    gs.cardClicked(card);
                }
            });
        }

    }

    public void cardClicked(Card card) {
        System.out.println("Card clicked: " + card.toString());
        if (playerHand.Cards.contains(card)) {
            cbq.cardBattleGame.moveSpecificCard(card, playerHand, playerField);
            layoutCards();
        }
    }

    @Override
    public void render(float delta) {

        shapeRenderer.setProjectionMatrix(this.batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(0,0,stage.getWidth(), stage.getHeight());
        shapeRenderer.end();

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
