package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.board.GameObject;
import sample.board.Table;
import sample.summoner.SummonerController;
import screens.winner.WinnerDisplay;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Game extends Application {
    static GridPane root;
    private Stage primaryStage;
    private Table table;

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        primaryStage.setTitle("School Rumble");

        table = new Table();
        root = table.getTable();
        setBackground();
        table.getEndTurn().setOnMousePressed(changeTurn);

        Scene scene = new Scene(root, 1920, 1080);
        primaryStage.setFullScreen(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static GridPane getRoot() {
        return root;
    }

    private void setBackground() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Robbin\\Desktop\\ART\\2x\\GB2x.png");
        Image image = new Image(inputStream);
        BackgroundImage bi = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bi));
    }

    public void newGame() {
        table = new Table();
        root = table.getTable();
        GameObject.setNode(root);
        try {
            setBackground();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        table.getEndTurn().setOnMousePressed(changeTurn);
        primaryStage.getScene().setRoot(root);
    }

    EventHandler<MouseEvent> changeTurn = event -> {
        SummonerController summonerPlayer = table.getSummonerPlayer();
        SummonerController summonerEnemy = table.getSummonerEnemy();
        if (summonerPlayer.getHealth() > 0 && summonerEnemy.getHealth() > 0) {
            table.getMatch().turn();
        } else {
            SummonerController summonerController = summonerPlayer.getHealth() > 0 ? summonerPlayer : summonerEnemy;
            WinnerDisplay winnerDisplay = new WinnerDisplay(summonerController, this);
            root.getChildren().add(winnerDisplay.getWinnerDisplay());
        }
    };

}
