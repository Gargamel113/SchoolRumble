package screens.main_menu;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu {
    private BorderPane borderPane;
    private String title;

    public MainMenu(String title) {
        this.borderPane = new BorderPane();
        this.title = title;
    }

    public void build() {
        VBox gameTitle = new VBox();
        gameTitle.getChildren().add(title(this.title));
        VBox buttons = buttons();

        gameTitle.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setAlignment(Pos.CENTER);

        borderPane.setTop(gameTitle);
        borderPane.setCenter(buttons);
    }

    public BorderPane getMainMenu() {
        return borderPane;
    }

    private Label title(String title) {
        Label label = new Label(title);
        label.setTextFill(Color.YELLOW);
        label.setFont(Font.font("Tahoma", FontWeight.BOLD, 120));
        return label;
    }

    private VBox buttons() {
        VBox vBox = new VBox();
        Button newGame = createButton("New Game");
        Button cardCollection = createButton("Collection");
        Button quit = createButton("Quit");

        vBox.getChildren().addAll(newGame, cardCollection, quit);
        return vBox;
    }

    private Background background() {
        return null;
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMinWidth(400);
        button.setMinHeight(200);

        return button;
    }
}
