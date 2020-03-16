package sample.ai.components;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import sample.ai.strategies.AbstractStrategy;
import sample.board.GameObject;
import sample.card.model.Card;
import sample.card.model.State;
import sample.summoner.SummonerView;

import java.util.List;

public class AiGameBoard {
    List<Card> playableCards;
    List<Card> cardsToAttack;

    public AiGameBoard(List<Card> playableCards, List<Card> cardsToAttack){
        this.playableCards = playableCards;
        this.cardsToAttack = cardsToAttack;
    }

    public void attackOpponent(AbstractStrategy strategy) {
        TilePane gameBoard = GameObject.getOpponentGB();
        TilePane opponentGameBoard = GameObject.getPayerGB();

        findActiveCards(gameBoard);

        while (playableCards.size() > 0) {

            findActiveCards(gameBoard);
            findCardsToAttack(opponentGameBoard);
            if (playableCards.size() > 0) {
                calculateStrategy(strategy);
            }
        }
    }

    public void calculateStrategy(AbstractStrategy strategy) {
        int attack = strategy.whatToAttack();
        if (attack != -1) {
            attackCard(attack);
        } else {
            attackSummoner();
        }
    }

    private void attackCard(int i) {
        Card attack = playableCards.get(0);
        Card target = cardsToAttack.get(i);

        fireEvent(attack);
        fireEvent(target);
    }

    private void attackSummoner() {
        Card attacker = playableCards.get(0);
        SummonerView sum = (SummonerView) GameObject.getPlayerSummoner().getChildren().get(0);

        fireEvent(attacker);
        fireEvent(sum);
    }

    private void findCardsToAttack(TilePane tilePane) {
        cardsToAttack.clear();
        tilePane.getChildren().forEach((card) -> {
            cardsToAttack.add((Card) card);
        });
    }

    private void findActiveCards(TilePane tilePane) {
        playableCards.clear();
        tilePane.getChildren().forEach((card) -> {
            if (((Card) card).getState() == State.ACTIVE) {
                playableCards.add(((Card) card));
            }
        });
    }

    public void fireEvent(Node tilePane) {
        Event.fireEvent(tilePane, new MouseEvent(MouseEvent.MOUSE_PRESSED, 10, 10, 20, 20, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    }

    public void fireEvent(SummonerView summoner) {
        Event.fireEvent(summoner, new MouseEvent(MouseEvent.MOUSE_PRESSED, 10, 10, 20, 20, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    }
}
