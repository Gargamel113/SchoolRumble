package sample.card.components.health;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

public class HealthView extends Pane {

    public HealthView() {
        this.setPrefHeight(40);
        this.setPrefWidth(40);
        this.setStyle("-fx-background-color: rgb(200, 20, 20);");
    }

    public void updateView(int hp){
        Label label = (Label) getChildren().get(0);
        label.setText(""+hp);
    }

    public HealthView getView() {
        return this;
    }

    public void fontSetup(int hp){
        Label label = new Label(""+hp);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));

        label.setPrefWidth(this.getPrefWidth());
        label.setPrefHeight(this.getPrefHeight());
        label.setAlignment(Pos.CENTER);


        this.getChildren().add(label);
    }
}