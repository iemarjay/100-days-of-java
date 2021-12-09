import java.util.*;
import java.util.stream.Collectors;

import org.assertj.core.util.VisibleForTesting;

public abstract class Player {

    private static final int STARTING_HAND_SIZE = 3;
    private static final int MAXIMUM_HAND_SIZE = 5;
    private static final int MAXIMUM_HEALTH = 30;
    private static final int MAXIMUM_MANA_SLOTS = 10;

    Deck deck;
    private final ArrayList<Card> hand = new ArrayList<>();

    private int health;
    private int mana = 0;
    private int manaSlot = 0;

    public Player() {
        this.deck = Deck.defaultDeck();
        this.health = MAXIMUM_HEALTH;
    }

    @VisibleForTesting
    Player(Deck deck, int health, int manaSlot) {
        this.deck = deck;
        this.health = health;
        this.manaSlot = manaSlot;
    }


    public Card nextCard() {
        return pickCard(cardsManaCanAfford());
    }

    public int getHealth() {
        return health;
    }

    void drawCard() {
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

    Player increaseManaSlot() {
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

    Card play() throws NoPlayableCardException {
        if (!hasPlayableCards()) {
            throw new NoPlayableCardException();
        }
        Card card = nextCard();
        hand.remove(card);
        mana -= card.getCost();

        return card;
    }

    void receiveDamage(Card card) {
        health -= card.getCost();
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    private List<Card> cardsManaCanAfford() {
        return hand.stream()
                .filter(card -> mana >= card.getCost())
                .collect(Collectors.toList());
    }

    protected abstract  Card pickCard(List<Card> playableCards);
}
