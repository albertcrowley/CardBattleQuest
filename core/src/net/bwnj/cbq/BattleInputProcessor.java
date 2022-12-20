package net.bwnj.cbq;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import net.bwnj.cbq.graphics.Card;

import java.util.ArrayList;
import java.util.List;

public class BattleInputProcessor implements InputProcessor {

    List<Card> cards = new ArrayList<>();
    List<InputListener> inputListeners = new ArrayList<InputListener>();

    public void clear() {
        cards.clear();
    }

    public void addCard(Card c) {
        cards.add(c);
    }

    public void addListener(InputListener _inputListener) {
        inputListeners.add(_inputListener);
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
//        Vector3 converted = GameScreen.camera.unproject(new Vector3(screenX, screenY, 0));
//        System.out.println (System.out.printf("Got a click at %d,%d          ", screenX, screenY));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 converted = OldGameScreen.camera.unproject(new Vector3(screenX, screenY, 0));
//        System.out.println (System.out.printf("mouse up!!!!: %f,%f            ", converted.x, converted.y));

        System.out.println ("Testing cards " + cards.size() );

        for (Card c : cards) {
            Rectangle boundingRec = new Rectangle(1,1,2,2); // c.getBoundingRectangle();
            if (boundingRec != null) {
//                System.out.println ("testing " + converted + " against " + c.getBoundingRectangle());
                if ( converted.x > boundingRec.x && converted.x < boundingRec.x + boundingRec.width && converted.y > boundingRec.y && converted.y < boundingRec.y + boundingRec.height) {
                    for (InputListener il : inputListeners) {
                        il.processClick(c);
                    }
                }

            }
        }

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
