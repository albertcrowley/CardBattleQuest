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

	@Override
	public void create() {

		// someday a menu here

		startBattle();

	}

	public void startBattle() {
		List<Location> locations = Arrays.asList(new Location[]{
				new Location("monsterDeck", DeckBuilder.getBaseMonsterDeck()),
				new Location("playerDeck", DeckBuilder.getStandardPlayingCardDeck()),
				new Location("monsterField", new Pile()),
				new Location("playerHand", new Pile()),
				new Location("playerField", new Pile())});


		game = new net.bwnj.cardbattle.Engine.Game(locations);


		gameScreen = new GameScreen(game);
		gameScreen.initBattle();
		setScreen(gameScreen);
		inputProcessor = new BattleInputProcessor();
		Gdx.input.setInputProcessor(inputProcessor);
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
