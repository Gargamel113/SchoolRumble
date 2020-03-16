package sample.card.components.model;

import javafx.scene.layout.TilePane;
import sample.board.GameObject;
import sample.card.model.Card;
import sample.card.model.State;

public enum Selection {

    INSTANCE;

    Card[] cards = new Card[2];

    public void newTurn() {
        cards[0] = null;
        cards[1] = null;
    }

    public Card getCard(int index) {
        return index == 0 ? cards[0] : cards[1];
    }

    public void selectCard(Card card) {
        if (isEmpty()) {
            setIndexZero(card);
        } else {
            scanNewCard(card);
        }
    }

    public void addToBattlefield(Card card) {
        card.setState(State.SLEEP);
        getGameBoard(card).getChildren().add(card);
        card.onField();
        card.getSummoner().setMana(card.getManaController().getMana());
    }

    //WILL ALWAYS BE OWN CARDS, ADDS CARD AND SETS THE CARD TO BE ACTIVE
    private void setIndexZero(Card card) {
        cards[0] = card;
        cards[0].setSelected();
    }

    //WILL ALWAYS BE OPPONENT CARD TO CALLING SUMMONER
    private void setIndexOne(Card card) {
        cards[1] = card;
    }

    //HANDLE WETHER THE CARD IS OPPONENT OR NOT
    private void scanNewCard(Card card) {
        if (compareSummoners(card)) {
            if (comapareCard(card)) {
                deselectCard();
            } else {
                changeActiveCard(card);
            }
        } else {
            setIndexOne(card);
        }
    }

    //SET THE OLD CARD TO NOT BE ACTIVE
    private void changeActiveCard(Card card) {
        cards[0].setSelected();
        setIndexZero(card);
        cards[1] = null;
    }

    //CHECKS IF INDEX 0 IS EMPTY AS IT WILL ALWAYS BE EMPTY IF 0 IS EMPTY
    private boolean isEmpty() {
        return cards[0] == null;
    }

    //CHECK WHAT SUMMONER OWNS THE CARD
    private boolean compareSummoners(Card card) {
        return card.getSummoner().equals(cards[0].getSummoner());
    }

    private boolean comapareCard(Card card) {
        return cards[0].equals(card);
    }

    private void deselectCard() {
        cards[0].setSelected();
        cards[0] = null;
        cards[1] = null;
    }

    private TilePane getGameBoard(Card card) {
        boolean parent = card.getParent().getId().equals("playerCardHolderPH");
        return parent ? GameObject.getPayerGB() : GameObject.getOpponentGB();
    }
}
