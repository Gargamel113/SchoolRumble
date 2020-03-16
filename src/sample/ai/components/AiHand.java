package sample.ai.components;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import sample.board.GameObject;
import sample.card.model.Card;

import java.util.ArrayList;
import java.util.List;

public class AiHand {

    private List<Card> cardsInHand;
    private List<Card> playableCards;

    public AiHand() {
        this.cardsInHand = new ArrayList<>();
        this.playableCards = new ArrayList<>();
    }

    public void addCardsToBattleField(int mana) {

        findPlayableCards(mana);

        while (mana > 0) {
            if (playableCards.size() == 0 || !gameBoardNotFull()) {
                break;
            }

            mana -= chooseCardFromHand().getManaController().getMana();
            findPlayableCards(mana);
        }

    }

    //TODO SET BACK TO 9
    private boolean gameBoardNotFull() {
        return GameObject.getOpponentGB().getChildren().size() < 10;
    }

    public void updateCardInHand() {
        cardsInHand.clear();
        GameObject.getOpponentCH().getChildren().forEach((c) -> {
            cardsInHand.add(((Card) c));
        });
    }

    public void findPlayableCards(int currentMana) {
        playableCards.clear();
        cardsInHand.forEach((card) -> {
            if (card.getMana() <= currentMana) {
                playableCards.add(card);
            }
        });
    }

    public Card chooseCardFromHand() {
        int ran = (int) (Math.random() * playableCards.size());

        addCardToGB(playableCards.get(ran));

        return playableCards.remove(ran);
    }

    public void addCardToGB(Node tilePane) {
        Event.fireEvent(tilePane, new MouseEvent(MouseEvent.MOUSE_PRESSED, 10, 10, 20, 20, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    }

}
