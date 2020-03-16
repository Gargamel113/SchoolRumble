package sample.card.components.mana;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ManaView extends Pane {
    String mana;

    public ManaView() {
        this.setPrefHeight(40);
        this.setPrefWidth(40);
        this.setStyle("-fx-background-color: rgb(20, 20, 200);");
    }

    public void fontSetup(int mana) {
        Label label = new Label("" + mana);
        label.setTextFill(Color.WHITE);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        label.setPrefWidth(this.getPrefWidth());
        label.setPrefHeight(this.getPrefHeight());
        label.setAlignment(Pos.CENTER);

        this.getChildren().add(label);
    }

    public void updateView(int mana) {
        Label label = (Label) getChildren().get(0);
        label.setText("" + mana);
    }

    public ManaView getView() {
        return this;
    }
}
