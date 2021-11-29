import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        player = new CPU();
    }

    @Test
    public void test_that_player_starts_with_30_lives_and_0_mana_slots() {
        assertThat(player.getHealth()).isEqualTo(30);
        assertThat(player.getManaSlots()).isEqualTo(0);
    }


    @Test
    public void test_that_player_starts_a_deck_of_20_card() {
        int[] costs = player.deck.get().stream().mapToInt(Card::getCost).toArray();

        assertThat(costs.length).isEqualTo(20);
        assertThat(costs).isEqualTo(new int[]{0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8});
    }

    @Test
    public void test_that_player_can_draw_card_from_deck() {
        player.drawCard();

        assertThat(player.getHand().size()).isEqualTo(1);
    }

    @Test
    public void test_that_player_can_draw_initial_hand() {
        assertThat(player.getHand().size()).isEqualTo(0);

        player.drawInitialHandFromDeck();

        assertThat(player.getHand().size()).isEqualTo(3);
    }

    @Test
    public void test_that_player_mana_slot_can_be_increased_to_the_maximum_of_10() {
        player.incrementManaSlot();
        assertThat(player.getManaSlots()).isEqualTo(1);

        Player player1 = new CPU();
        player1.setManaSlot(9);

        assertThat(player1.getManaSlots()).isEqualTo(9);
    }

    @Test
    public void test_that_player_mana_can_be_refilled() {
        assertThat(player.getMana()).isEqualTo(0);

        player.setManaSlot(5);
        player.refillMana();
        assertThat(player.getMana()).isEqualTo(5);
    }

    static class CPU extends Player {

        @Override
        protected Card pickCard(List<Card> playableCards) {

            return playableCards.get(new Random().nextInt(playableCards.size()));
        }

    }
}
