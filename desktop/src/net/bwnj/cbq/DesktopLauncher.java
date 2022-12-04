package net.bwnj.cbq;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import net.bwnj.cbq.CardBattleQuest;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(120);
//		config.useVsync(false);
		config.setTitle("CardBattleQuest");
		config.setWindowedMode(720, 1280);
		new Lwjgl3Application(new CardBattleQuest(), config);
	}
}
