package net.bwnj.kingdomquest;

public class RandoCLI {
//
//    static Game game;
//    static Integer score;
//    static boolean gameOver = false;
//    static String gameState = "playing";
//
//    static Random rand = new Random();
//
//    static void initGame() {
//
//        Location l1 = new Location("hand");
//        Location l2 = new Location("drawpile", DeckBuilder.getRandoDeck());
//        Location l3 = new Location("table");
//        Location l4 = new Location("monsters", DeckBuilder.getRandoDeck());
//        Location l5 = new Location("field");
//
//        RandoCLI.game = new Game(List.of(new Location[]{l1, l2, l3, l4, l5}));
//
//        game.get("drawpile").Cards.shuffle();
//        game.get("monsters").Cards.shuffle();
//        game.moveCards(5, "drawpile", "hand");
//        game.moveCards(rand.nextInt(2)+1, "monsters", "field");
//
//        score = 0;
//    }
//
//    public static boolean printGameState() {
//        System.out.println("Your hand:");
//        System.out.println(game.get("hand"));
//        System.out.println("\nMonsters!");
//        System.out.println(game.get("field"));
//        System.out.println("\nCards on the table");
//        System.out.println(game.get("table"));
//
//        System.out.println("Your score: " + score);
//
//
//        return true;
//    }
//
//    public static Integer chooseCard() {
//        try {
//            Scanner in = new Scanner(System.in);
//            int a = in.nextInt();
//            System.out.println("You entered integer " + a);
//            return a;
//        } catch(InputMismatchException ime) {
//            System.out.println("That wasn't a number, please try again");
//            return chooseCard();
//        }
//
//    }
//
//    public static Object getInput() {
//        if (gameState.equals("playing")) {
//            System.out.println ("(p)lay more cards - or choose to (f)ight?");
//            try {
//                Scanner in = new Scanner(System.in);
//                String s = in.nextLine();
//                if (s.toLowerCase().startsWith("p")) {
//                    return chooseCard();
//                }
//                if (s.toLowerCase().startsWith("f")) {
//                    gameState = "fighting";
//                    return new String("fighting");
//                }
//            } catch(InputMismatchException ime) {
//                System.out.println(" p or f ?");
//                return getInput();
//            }
//        }
//        return null;
//    }
//
//    static void tick() {
//
//        Object input = getInput();
//        if (gameState.equals("playing")) {
//            Integer cardNumber = (Integer) input;
//            game.moveSpecificCard(cardNumber-1, "hand", "table");
//            //now draw
//            game.moveCards(1,"drawpile", "hand");
//        } else if (gameState.equals("fighting")) {
//            fight();
//        }
//    }
//
//    static void fight() {
//        Integer allMonsterToughness = game.get("field").Cards.stream().map( c -> c.Architype.Toughness).mapToInt(Integer::valueOf).sum();
//        System.out.println ("Total monster toughness is " + allMonsterToughness);
//
//        Integer allPlayerPower = game.get("table").Cards.stream().map( c -> c.Architype.Power).mapToInt(Integer::valueOf).sum();
//        System.out.println ("Total player power is " + allPlayerPower);
//
//        if (allPlayerPower >= allMonsterToughness) {
//            System.out.println("YOU WIN!");
//            exit (0);
//        }
//        System.out.println ("GAME OVER");
//        exit(0);
//    }
//
//
//    public static void main(String[] args) {
//        try {
//            System.out.println("Welcome to Rando Battle");
//
//
//            initGame();
//
//            while ( ! gameOver) {
//                printGameState();
//                tick();
//            }
//
//            System.out.println("GAME OVER!");
//        } catch (Exception e) {
//            System.out.println(e);
//            throw new RuntimeException(e);
//        }
//
//    }
}
