package net.bwnj.cbq;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

public class BattleInputProcessor implements InputProcessor {

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

        Vector3 converted = GameScreen.camera.unproject(new Vector3(screenX, screenY, 0));

        System.out.println (System.out.printf("Got a click at %d,%d          ", screenX, screenY));
        System.out.println (System.out.printf("Converted: %f,%f            ", converted.x, converted.y));
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
