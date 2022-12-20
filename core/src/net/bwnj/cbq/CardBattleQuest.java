package net.bwnj.cbq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;

import net.bwnj.cardbattle.Engine.CardBattleGame;
import net.bwnj.cardbattle.Engine.DeckBuilder;
import net.bwnj.cardbattle.Engine.Location;
import net.bwnj.cardbattle.Engine.Pile;
import net.bwnj.cbq.screens.GameScreen;
import net.bwnj.cbq.screens.SplashScreen;

import java.util.Arrays;
import java.util.List;


public class CardBattleQuest extends Game {

	GameScreen gameScreen;
	BattleInputProcessor inputProcessor;
	public CardBattleGame cardBattleGame;

	public static final String PLAYER_HAND = "playerHand";
	public static final String MONSTER_HAND = "playerHand";
	public static final String MONSTER_FIELD = "monsterField";
	public static final String PLAYER_FIELD = "playerField";
	public static final String MONSTER_DECK = "monsterDeck";
	public static final String PLAYER_DECK = "playerDeck";

	public final int WORLD_WIDTH = 720;
	public final int WORLD_HEIGHT = 1280;

	public Camera camera;

	private Screen currentScreen;

	@Override
	public void create() {

		// someday a menu here
		camera = new OrthographicCamera();

		SplashScreen splash = new SplashScreen(this);
		currentScreen = splash;
		setScreen(splash);

	}

	public void startNewGame() {
		startBattle();
		currentScreen.dispose();
		GameScreen gs = new GameScreen(this);
		currentScreen = gs;
		gs.initBattle();
		setScreen(gs);

	}

	public void startBattle() {
		List<Location> locations = Arrays.asList(new Location[]{
				new Location(MONSTER_DECK, DeckBuilder.getBaseMonsterDeck()),
				new Location(MONSTER_FIELD, new Pile()),
				new Location(PLAYER_DECK, DeckBuilder.getStandardPlayingCardDeck()),
				new Location(PLAYER_HAND, new Pile()),
				new Location(PLAYER_FIELD, new Pile())});

		cardBattleGame = new CardBattleGame(locations);

	}


	@Override
	public void dispose() {
		super.dispose();
		gameScreen.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		currentScreen.resize(width, height);
	}
}
