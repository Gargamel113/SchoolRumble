package sample.deck;

import sample.card.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> deck;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public Card getCard(int index) {
        return deck.get(index);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public int getW() {
        return 144;
    }

    public int getH() {
        return 192;
    }
}
