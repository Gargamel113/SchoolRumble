package sample.board;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import sample.ai.Ai;
import sample.card.model.Card;
import sample.card.model.State;
import sample.summoner.SummonerController;

public class Match {
    private SummonerController summonerPlayer;
    private SummonerController summonerEnemy;
    private SummonerController currentSummoner;
    private Ai ai;

    private int mana, turn;

    public Match(SummonerController summoner1, SummonerController summoner2) {
        this.summonerPlayer = summoner1;
        this.summonerEnemy = summoner2;
        this.mana = summonerPlayer.getMana();

        currentSummoner = summonerPlayer;

        ai = new Ai(summonerEnemy);
        turn = 1;
    }

    public void turn() {
        turn++;

        if (mana < 10) {
            mana += turn % 2 == 0 ? 1 : 0;
        }
        changeTurn();
    }

    private void changeTurn() {
        if (currentSummoner == summonerPlayer) {
            System.out.println("Enemy turn");

            resetEnemyState();

            summonerPlayer.setCardsInactive();
            summonerEnemy.setCardsActive();
            currentSummoner = summonerEnemy;

            summonerEnemy.drawCardFromDeck();

        } else {
            System.out.println("Player turn");

            resetPlayerState();

            summonerEnemy.setCardsInactive();
            summonerPlayer.setCardsActive();
            currentSummoner = summonerPlayer;

            summonerPlayer.drawCardFromDeck();
        }

        currentSummoner.resetMana(mana);

        if (currentSummoner.equals(summonerEnemy)) {
            enemyTurn();
        }

    }

    private void resetPlayerState() {
        TilePane player = GameObject.getPayerGB();
        player.getChildren().forEach(this::setCardStateActive);
    }

    private void resetEnemyState() {
        TilePane enemy = GameObject.getOpponentGB();
        enemy.getChildren().forEach(this::setCardStateActive);
    }

    private void setCardStateActive(Node c){
        Card card = (Card) c;
        card.setState(State.ACTIVE);
    }

    private void enemyTurn() {
        ai.play();

//        ai.endTurn();

        new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> ai.endTurn());
        }).start();

    }
}
