package net.bwnj.cardbattle.Engine;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maximeroussy.invitrode.WordGenerator;

import net.bwnj.cbq.graphics.Card;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeckBuilder {

    public static Pile getFiveCardDeck() {
        List<Card> cardSet = new ArrayList<>();
        cardSet.add(new Card(new CardArchitype("one")));
        cardSet.add(new Card(new CardArchitype("two")));
        cardSet.add(new Card(new CardArchitype("three")));
        cardSet.add(new Card(new CardArchitype("four")));
        return new Pile(cardSet);
    }

    static CardArchitype architypeHelper(String name, int power) {
        CardArchitype ca = new CardArchitype(name);
        ca.power = power;
        return ca;
    }


    public static Pile getRandoDeck() {
        List<Card> cardSet = new ArrayList<>();
        WordGenerator wg = new WordGenerator();
        Random random = new Random();

        for (int i=0; i< 60; i++) {
            cardSet.add(
                    new Card(
                            new CardArchitype(
                                    wg.newWord(random.nextInt(4) + 4),
                                    "Monster",
                                    1,
                                    random.nextInt(4) + 1,
                                    random.nextInt(4) + 1
                            )
                    )
            );
        }
        return new Pile(cardSet, "Playing Cards");
    }


    public static Pile getBaseMonsterDeck() {
        List<Card> cardSet = new ArrayList<>();
        JsonNode root;
        JsonNode baseMonsterDeck;


        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("BaseMonsterDeck.json")) {
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readValue(in, JsonNode.class);


            JsonNode decks = root.get("decks");
            for (int i = 0; i < decks.size(); i++) {
                baseMonsterDeck = decks.get(i);
                if (baseMonsterDeck.get("name").textValue().equals("BaseMonsterDeck")) {
                    JsonNode cards = baseMonsterDeck.get("cards");
                    for (int j = 0; j < cards.size(); j++) {
                        JsonNode card = cards.get(j);
                        CardArchitype ca = new CardArchitype(card.get("name").textValue());
                        ca.power = card.get("power").intValue();
                        ca.toughness = card.get("toughness").intValue();
                        ca.cost = card.get("cost").intValue();
                        for (int c=0; c < card.get("count").intValue(); c++) {
                            cardSet.add(new Card(ca));
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return new Pile(cardSet, "Playing Cards");
    }

    public static Pile getStandardPlayingCardDeck() {
        List<Card> cardSet = new ArrayList<>();
        for (String suit : new String[]{"Hearts", "Spades", "Diamonds", "Clubs"}) {
            cardSet.add(new Card(architypeHelper("Ace of " + suit, 1)));
            cardSet.add(new Card(architypeHelper("King of "+ suit, 13)));
            cardSet.add(new Card(architypeHelper("Queen of " + suit, 12)));
            cardSet.add(new Card(architypeHelper("Jack of " + suit, 11)));
            for (int i = 2; i <= 10; i++) {
                CardArchitype ca = new CardArchitype(i + " of " + suit);
                ca.power = i;
                cardSet.add(new Card(ca));
            }
        }
        return new Pile(cardSet, "Playing Cards");
    }
}
