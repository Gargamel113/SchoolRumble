package sample.board;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EndTurnButton extends Pane {

    public EndTurnButton() {
        this.setPrefHeight(100);
        this.setPrefWidth(200);
        this.setStyle("-fx-background-color: rgb(150, 150, 150);");

        Label label = new Label("END TURN");
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));

        label.setPrefWidth(this.getPrefWidth());
        label.setPrefHeight(this.getPrefHeight());
        label.setAlignment(Pos.CENTER);

        this.getChildren().add(label);
    }

    public Pane getEndTurnButton() {
        return this;
    }
}
