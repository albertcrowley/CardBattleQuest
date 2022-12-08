package net.bwnj.cbq;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import net.bwnj.cardbattle.Engine.DeckBuilder;
import net.bwnj.cardbattle.Engine.Location;
import net.bwnj.cardbattle.Engine.Pile;

import java.util.Arrays;
import java.util.List;


public class CardBattleQuest extends Game {

	GameScreen gameScreen;
	BattleInputProcessor inputProcessor;
	net.bwnj.cardbattle.Engine.Game game;

	public static final String PLAYER_HAND = "playerHand";
	public static final String MONSTER_HAND = "playerHand";
	public static final String MONSTER_FIELD = "monsterField";
	public static final String PLAYER_FIELD = "playerField";
	public static final String MONSTER_DECK = "monsterDeck";
	public static final String PLAYER_DECK = "playerDeck";

	@Override
	public void create() {

		// someday a menu here

		startBattle();

	}

	public void startBattle() {
		List<Location> locations = Arrays.asList(new Location[]{
				new Location(MONSTER_DECK, DeckBuilder.getBaseMonsterDeck()),
				new Location(MONSTER_FIELD, new Pile()),
				new Location(PLAYER_DECK, DeckBuilder.getStandardPlayingCardDeck()),
				new Location(PLAYER_HAND, new Pile()),
				new Location(PLAYER_FIELD, new Pile())});


		game = new net.bwnj.cardbattle.Engine.Game(locations);


		inputProcessor = new BattleInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);

		gameScreen = new GameScreen(game, inputProcessor);
		gameScreen.initBattle();
		setScreen(gameScreen);
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
		gameScreen.resize(width, height);
	}
}
