import java.util.*;

import static java.util.stream.Collectors.toCollection;

public abstract class Player {

    private static final int STARTING_HAND_SIZE = 3;
    private static final int MAXIMUM_HAND_SIZE = 5;
    private static final int MAXIMUM_HEALTH = 30;
    private static final int MAXIMUM_MANA_SLOTS = 10;

    public final Deck deck = new Deck(0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 5, 5, 6, 6, 7, 8);
    private final ArrayList<Card> hand = new ArrayList<>();

    private int health = MAXIMUM_HEALTH;
    private int mana = 0;
    private int manaSlot = 0;


    public Card nextCard() {
        return pickCard(cardsManaCanAfford());
    }

    public int getHealth() {
        return health;
    }

    public void drawCard() {
        if (deck.size() == 0) {
            health--;
            return;
        }

        Card card = deck.deal();

        if (hand.size() < MAXIMUM_HAND_SIZE)
            hand.add(card);

    }

    public Player drawInitialHandFromDeck() {
        for (int i = 0; i < STARTING_HAND_SIZE; i++) {
            drawCard();
        }

        return this;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    Player incrementManaSlot() {
        if (manaSlot < MAXIMUM_MANA_SLOTS) manaSlot++;

        return this;
    }

    public void setManaSlot(int slot) {
        manaSlot = slot;
    }

    public int getManaSlots() {
        return manaSlot;
    }

    public int getMana() {
        return mana;
    }

    Player refillMana() {
        if (mana == 0) mana = manaSlot;

        return this;
    }

    boolean hasPlayableCards() {
        return cardsManaCanAfford().size() > 0;
    }

    public void play(Card card) {
        hand.remove(card);
        mana -= card.getCost();
    }

    public void receiveDamage(Card card) {
        health -= card.getCost();
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    private ArrayList<Card> cardsManaCanAfford() {
        return hand.stream()
                .filter(card -> mana >= card.getCost())
                .collect(toCollection(ArrayList::new));
    }

    protected abstract  Card pickCard(List<Card> playableCards);

}
