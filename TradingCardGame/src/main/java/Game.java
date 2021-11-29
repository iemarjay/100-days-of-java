import java.util.Random;

public class Game {

    private Player winner;
    private Player activePlayer;
    private Player opponentPlayer;

    public Game(Player player, Player player1) {
        randomlyPickActiveAndOpponentPlayer(player, player1);

        activePlayer.drawInitialHandFromDeck();
        opponentPlayer.drawInitialHandFromDeck().drawCard();
    }

    public void start() {
            prepareForActivePlayerTurn();
            newTurn();
            switchPlayers();
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public Player getOpponentPlayer() {
        return opponentPlayer;
    }

    public Player getWinner() {
        return winner;
    }

    private void newTurn() {
        if (activePlayer.hasPlayableCards()) {
            Card card = activePlayer.nextCard();

            activePlayer.play(card);
            opponentPlayer.receiveDamage(card);
        }
    }

    private void randomlyPickActiveAndOpponentPlayer(Player player, Player player1) {
        Random random = new Random();

        this.activePlayer = random.nextBoolean() ? player : player1;
        this.opponentPlayer = activePlayer.equals(player) ? player1 : player;
    }

    private void switchPlayers() {
        Player active = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = active;
    }

    private void prepareForActivePlayerTurn() {
        activePlayer
                .incrementManaSlot()
                .refillMana()
                .drawCard();
    }
}
