package sample.board;

import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import sample.Game;


public class GameObject {

    static Node node = Game.getRoot();

    public static TilePane getOpponentGB() {
        return (TilePane) node.lookup("#enemyGameBoardPH");
    }

    public static TilePane getOpponentCH() {
        return (TilePane) node.lookup("#enemyCardHolderPH");
    }

    public static TilePane getPayerGB() {
        return (TilePane) node.lookup("#playerGameBoardPH");
    }

    public static TilePane getPlayerCH() {
        return (TilePane) node.lookup("#playerCardHolderPH");
    }

    public static TilePane getPlayerSummoner() {
        return (TilePane) node.lookup("#playerSummonerPH");
    }

    public static Node getEndButton() {
        return node.lookup("#endTurnPH");
    }

    public static void setNode(Node node){
        GameObject.node = node;
    }
}
