package screens.winner;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import sample.Game;
import sample.summoner.SummonerController;

public class WinnerDisplay extends BorderPane {
    SummonerController winner;
    Game matchReference;
    Pane display;

    public WinnerDisplay(SummonerController summoner, Game match) {
        this.winner = summoner;
        this.matchReference = match;
        display();
    }

    public Pane getWinnerDisplay() {
        return display;
    }

    public void display() {
        display = new Pane();

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();
        Button continueButton = createButton("Continue");
        Button cancelButton = createButton("Cancel");
        Label label = fontSetup(winner.getName());

        display.setPrefWidth(width/2);
        display.setPrefHeight(height/2);
        display.setMinWidth(800);
        display.setMinHeight(500);
        display.setStyle("-fx-background-color: rgb(200, 20, 20);");
        display.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5))));
        display.setTranslateY(500);
        display.setTranslateX(500);

        label.setCenterShape(true);
        label.setTranslateX(230);
        label.setTranslateY(200);

        continueButton.setTranslateX(230);
        continueButton.setTranslateY(300);
        cancelButton.setTranslateX(450);
        cancelButton.setTranslateY(300);

        display.getChildren().add(label);
        display.getChildren().add(continueButton);
        display.getChildren().add(cancelButton);

        continueButton.setOnMousePressed(event -> matchReference.newGame());
        cancelButton.setOnMousePressed(event -> Platform.exit());

        setAlignment(display, Pos.CENTER);
        setCenter(display);
    }

    public Label fontSetup(String name) {
        Label label = new Label("" + name + " is the winner!!");
        label.setTextFill(Color.YELLOW);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 30));

        label.setPrefWidth(this.getPrefWidth());
        label.setPrefHeight(this.getPrefHeight());
        label.setAlignment(Pos.CENTER);

        return label;
    }

    private Button createButton(String text) {
        Button button = new Button(text);

        button.setPrefWidth(100);
        button.setPrefHeight(50);

        return button;
    }
}
