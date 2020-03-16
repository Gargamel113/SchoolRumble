package sample.ai;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import sample.ai.components.AiGameBoard;
import sample.ai.components.AiHand;
import sample.ai.strategies.AbstractStrategy;
import sample.ai.strategies.BoardStrategy;
import sample.board.GameObject;
import sample.card.model.Card;
import sample.summoner.SummonerController;

import java.util.ArrayList;
import java.util.List;

public class Ai {

    private SummonerController summoner;
    private AbstractStrategy strategy;
    private AiHand hand;
    private AiGameBoard aiGameBoard;
    private List<Card> playableCards;
    private List<Card> cardsToAttack;


    public Ai(SummonerController sum) {
        this.playableCards = new ArrayList<>();
        this.cardsToAttack = new ArrayList<>();

        this.summoner = sum;
        this.hand = new AiHand();

        this.strategy = new BoardStrategy(cardsToAttack, playableCards);
        this.aiGameBoard = new AiGameBoard(playableCards, cardsToAttack);
    }

    public void play() {
        hand.updateCardInHand();
        hand.addCardsToBattleField(summoner.getMana());
        aiGameBoard.attackOpponent(strategy);
    }

    public void endTurn() {
        System.out.println("Ai ended turn");
        pressEndTurn(GameObject.getEndButton());
    }

    public void pressEndTurn(Node tilePane) {
        Event.fireEvent(tilePane, new MouseEvent(MouseEvent.MOUSE_PRESSED, 10, 10, 20, 20, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
    }

    private void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
