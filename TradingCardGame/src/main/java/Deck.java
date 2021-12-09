import org.assertj.core.util.VisibleForTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

import static java.util.stream.Collectors.toCollection;

public class Deck {
    private Function<Integer, Integer> indexFinder;

    protected ArrayList<Card> deck;

    public Deck(Integer... values) {
        this.deck = Arrays.stream(values).map(Card::new).collect(toCollection(ArrayList::new));

        Random random = new Random();
        indexFinder = random::nextInt;
    }

    public ArrayList<Card> get() {
        return deck;
    }

    public int size() {
        return deck.size();
    }

    Card deal() {
        int index = indexFinder.apply(deck.size());

        Card card = deck.get(index);
        deck.remove(card);

        return card;
    }

    public static Deck defaultDeck() {
        return new Deck(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
    }

    @VisibleForTesting
    public void setIndexFinder(Function<Integer, Integer> indexFinder) {
        this.indexFinder = indexFinder;
    }
}
