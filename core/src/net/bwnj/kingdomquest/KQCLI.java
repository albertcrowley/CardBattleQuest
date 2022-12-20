package net.bwnj.kingdomquest;

import net.bwnj.cardbattle.Engine.DeckBuilder;
import net.bwnj.cardbattle.Engine.CardBattleGame;
import net.bwnj.cardbattle.Engine.Location;
import net.bwnj.cardbattle.Engine.Pile;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KQCLI {

    static CardBattleGame cardBattleGame;
    static Integer score;
    static boolean gameOver = false;


    static void initGame() {
        Pile p = DeckBuilder.getStandardPlayingCardDeck();

        Location l1 = new Location("hand");
        Location l2 = new Location("drawpile", p);
        Location l3 = new Location("table");
        KQCLI.cardBattleGame = new CardBattleGame(List.of(new Location[]{l1, l2, l3}));

        cardBattleGame.get("drawpile").Cards.shuffle();
        cardBattleGame.moveCards(5, "drawpile", "hand");

        score = 0;
    }

    public static boolean printGameState() {
        System.out.println("Your hand:");
        System.out.println(cardBattleGame.get("hand"));
        System.out.println("\nCards on the table");
        System.out.println(cardBattleGame.get("table"));

        System.out.println("Your score: " + score);


        return true;
    }

    public static Integer getInput() {

        try {
            Scanner in = new Scanner(System.in);

            int a = in.nextInt();
            System.out.println("You entered integer " + a);
            return a;
        } catch(InputMismatchException ime) {
            System.out.println("That wasn't a number, please try again");
            return null;
        }
    }

    static void tick() {
        Integer cardNumber = getInput();
        if (cardNumber != null) {
            cardBattleGame.moveSpecificCard(cardNumber-1, "hand", "table");
            //now draw
            cardBattleGame.moveCards(1,"drawpile", "hand");
        }

        scoreTheGame();
    }

    static void scoreTheGame() {
//        score = game.get("table").Cards.stream().map((card) -> card.Architype.Power).reduce(0, (subtotal, i) -> subtotal + i);
//
//        if (score > 21) {
//            System.out.println("\nSorry you lost. You went over 21!  \nYour ending score was " + score);
//            gameOver = true;
//        }
//
//        if (score == 21) {
//            System.out.println("\nYOU WIN!  Perfect score of 21");
//            gameOver = true;
//        }
//
//        if (game.get("hand").Cards.size() < 1) {
//            gameOver = true;
//        }

    }

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to 21 Counter");


            initGame();

            while ( ! gameOver) {
                printGameState();
                tick();
            }

            System.out.println("GAME OVER!");
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }

    }
}
