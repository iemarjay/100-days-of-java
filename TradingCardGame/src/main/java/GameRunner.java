public class GameRunner {
    private final Game game;

    public GameRunner(Game game) {
        this.game = game;
    }

    public void start() throws Player.NoPlayableCard {
        game.prepareForActivePlayerTurn();

        while (game.getActivePlayer().hasPlayableCards()) {
            game.newMove();
        }

        game.switchPlayers();
    }

}