import org.junit.Before;
import org.junit.Test;

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
    public void test_that_game_has_a_winner() {
        game.start();
        assertThat(game.getWinner()).isEqualTo(game.getActivePlayer());
        assertThat(game.getWinner()).isNotEqualTo(game.getOpponentPlayer());
    }

}
