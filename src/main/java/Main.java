public class Main {

    public static void main(String[] args) {
        playGame("**** GAME A ****", new GamePlay(), 0, null);
        playGame("**** GAME B ****", new GamePlay(new Wizard()), 10, null);
        playGame("**** GAME C ****", new GamePlay(new Barbarian(), new Wizard()), 0, 5);
    }

    private static void playGame(String gameTitle, GamePlay gamePlay, int initialPlayerHealth, Integer initialOpponentHealth) {
        System.out.println(gameTitle);
        gamePlay.player.health = initialPlayerHealth;
        if (initialOpponentHealth != null) {
            gamePlay.opponents.get(0).health = initialOpponentHealth;
        }
        for (int round = 1; round < 9; round++) {
            System.out.println("\tRound " + round);
            if (gamePlay.player.health > 0) {
                System.out.println("\t\tYou gained " + gamePlay.play() + " experience points during this round!!!!\n");
            }
            if (gamePlay.player.health <= 0) {
                System.out.println("\t\tBut your player died. Better luck next time.\n\n");
                break;
            }
        }
    }
}
