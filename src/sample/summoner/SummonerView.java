package sample.summoner;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class SummonerView extends Pane {
    private SummonerController controller;
    private Rectangle rectangle;

    public SummonerView() {
        this.setPrefHeight(200);
        this.setPrefWidth(200);
        this.setStyle("-fx-background-color: rgb(100, 100, 100);");
    }

    public SummonerView getView() {
        return this;
    }

    public void addChild(Pane pane) {
        getChildren().add(pane);
    }

    public SummonerController getController() {
        return controller;
    }

    public void setController(SummonerController controller) {
        this.controller = controller;
    }
}
