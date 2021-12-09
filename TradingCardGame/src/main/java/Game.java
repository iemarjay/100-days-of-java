import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Game {

    public Callable<Boolean> firstPlayerIsActive;

    private Player winner;
    private Player activePlayer;
    private Player opponentPlayer;

    public Game(Player player, Player player1) {
        randomlyPickActiveAndOpponentPlayer(player, player1);

        activePlayer.drawInitialHandFromDeck();
        opponentPlayer.drawInitialHandFromDeck().drawCard();

        Random random = new Random();
        firstPlayerIsActive = random::nextBoolean;
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

    void newMove() throws IllegalMoveException {
        Card card = null;
        try {
            card = activePlayer.play();
        } catch (NoPlayableCardException e) {
            throw new IllegalMoveException("Cant make move since there are no cards left.", e);
        }

        opponentPlayer.receiveDamage(card);

        if (opponentPlayer.isDead()) {
            winner = activePlayer;
        }
    }

    void switchPlayers() {
        if (opponentPlayer.isDead()) return;

        Player active = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = active;
    }

    void prepareForActivePlayerTurn() {
        activePlayer
                .increaseManaSlot()
                .refillMana()
                .drawCard();
    }

    private void randomlyPickActiveAndOpponentPlayer(Player player, Player player1) {
        Random random = new Random();

        this.activePlayer = random.nextBoolean() ? player : player1;
        this.opponentPlayer = activePlayer.equals(player) ? player1 : player;
    }
}
