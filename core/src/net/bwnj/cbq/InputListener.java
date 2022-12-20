package net.bwnj.cbq;

public interface InputListener {
    public void processClick(Object o);

    boolean touchUp(int x, int y, int pointer, int button);
}
