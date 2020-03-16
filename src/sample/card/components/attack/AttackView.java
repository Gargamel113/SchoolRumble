package sample.card.components.attack;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AttackView extends Pane {

    public AttackView() {
        this.setPrefHeight(40);
        this.setPrefWidth(40);
        this.setStyle("-fx-background-color: rgb(100, 100, 100);");
    }

    public void updateView(int attack) {
        Label label = (Label) getChildren().get(0);
        label.setText("" + attack);
    }

    public AttackView getView() {
        return this;
    }

    public void fontSetup(int attack){
        Label label = new Label(""+attack);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));

        label.setPrefWidth(this.getPrefWidth());
        label.setPrefHeight(this.getPrefHeight());
        label.setAlignment(Pos.CENTER);

        this.getChildren().add(label);
    }
}
