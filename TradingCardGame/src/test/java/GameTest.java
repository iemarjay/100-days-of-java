import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game(new PlayerTest.CPU(), new PlayerTest.CPU());
    }

    @Test
    public void test_game_set_active_and_opponent_player() {
        assertThat(game.getActivePlayer()).isNotNull();
        assertThat(game.getOpponentPlayer()).isNotNull();
        assertThat(game.getOpponentPlayer()).isNotEqualTo(game.getActivePlayer());
    }

    @Test
    public void test_that_both_players_has_correct_initial_hand() {
        assertThat(game.getActivePlayer().getHand().size()).isEqualTo(3);
        assertThat(game.getOpponentPlayer().getHand().size()).isEqualTo(4);
    }

    @Test
    public void test_that_game_can_be_prepare_for_new_turn() {
        game.prepareForActivePlayerTurn();
        Player initialActivePlayer = game.getActivePlayer();

        assertThat(initialActivePlayer.getMana()).isEqualTo(1);
        assertThat(initialActivePlayer.getManaSlots()).isEqualTo(1);
        assertThat(initialActivePlayer.getHand().size()).isEqualTo(4);
    }

    @Test
    public void test_that_game_active_player_play() throws Player.NoPlayableCard {
        Deck deck = new Deck(1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8, 0, 0);
        deck.setIndexFinder((Integer size) -> 0);

        Game game = new Game(new CustomCPU(deck), new CustomCPU(deck));
        Player active = game.getActivePlayer();

        game.prepareForActivePlayerTurn();
        game.newMove();

        assertThat(active.getMana()).isEqualTo(0);
        assertThat(game.getOpponentPlayer().getHealth()).isEqualTo(29);
    }


    @Test
    public void test_that_game_can_switch_players() {
        Player active = game.getActivePlayer();
        Player opponent = game.getOpponentPlayer();

        game.switchPlayers();

        assertThat(active).isNotEqualTo(game.getActivePlayer());
        assertThat(opponent).isNotEqualTo(game.getOpponentPlayer());
    }

    @Test
    public void test_that_game_cannot_switch_players_when_opponent_is_dead() {
        Player active = game.getActivePlayer();
        Player opponent = game.getOpponentPlayer();

        game.switchPlayers();

        assertThat(active).isNotEqualTo(game.getActivePlayer());
        assertThat(opponent).isNotEqualTo(game.getOpponentPlayer());
    }

    @Test
    public void test_that_opponent_player_dies_with_enough_moves() throws Player.NoPlayableCard {
        Deck deck = new Deck(8, 7, 6, 6, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 0, 0);
        deck.setIndexFinder((Integer size) -> 0);

        Game game = new Game(new CustomCPU(deck, 10), new CustomCPU(deck, 10));
        Player opponent = game.getOpponentPlayer();

        game.prepareForActivePlayerTurn();
        game.newMove();
        game.newMove();

        assertThat(opponent.isDead()).isEqualTo(true);
    }

    @Test
    public void test_that_game_sets_winner_when_opponent_dies() throws Player.NoPlayableCard {
        Deck deck = new Deck(8, 7, 6, 6, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 0, 0);
        deck.setIndexFinder((Integer size) -> 0);

        Game game = new Game(new CustomCPU(deck, 10), new CustomCPU(deck, 10));
        Player opponent = game.getOpponentPlayer();

        game.prepareForActivePlayerTurn();
        game.newMove();
        game.newMove();

        assertThat(opponent.isDead()).isTrue();
        assertThat(game.getWinner()).isEqualTo(game.getActivePlayer());
    }

    public static class CustomCPU extends Player {
        public CustomCPU(Deck deck) {
            super(deck, 10, 0);
        }
        public CustomCPU(Deck deck, int manaSlot) {
            super(deck, 10, manaSlot);
        }

        @Override
        protected Card pickCard(List<Card> playableCards) {
            return playableCards.get(0);
        }
    }
}
