package sample.card.model;

import javafx.geometry.Insets;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CardView {
    int width, height;
    InnerShadow highLight;
    Pane card;
    Image cardArtSource;
    ImageView cardArtDisplay;

    public CardView(int width, int height) {
        this.width = width;
        this.height = height;
        this.highLight = new InnerShadow();
        card = new Pane();

        try {
            this.cardArtSource = new Image(new FileInputStream("C:\\Users\\Robbin\\Desktop\\ART\\4x\\CardFront4x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        cardArtDisplay = new ImageView(cardArtSource);
        initCard();
    }

    private void initCard() {
        cardArtDisplay.setFitHeight(cardArtSource.getHeight() * 0.75);
        cardArtDisplay.setFitWidth(cardArtSource.getWidth() * 0.75);

        card.getChildren().add(cardArtDisplay);
        card.setPadding(new Insets(-5, -5, -5, -5));
        card.setMaxHeight(218);
        card.setMaxWidth(156);
    }

    public void highLight() {
        Glow glow = new Glow();
        glow.setLevel(0.9);
        card.setEffect(glow);
    }

    public void deHighLight() {
        Glow glow = new Glow();
        glow.setLevel(0);
        card.setEffect(glow);
    }

    public void sleep() {
        ColorAdjust ca = new ColorAdjust();

        ca.setBrightness(-0.4);
        card.setEffect(ca);
    }

    public void active() {
        ColorAdjust ca = new ColorAdjust();
        ca.setBrightness(0);
        card.setEffect(ca);
    }

    public void setNewImage(Image image) {
        card.getChildren().clear();
        cardArtDisplay.setImage(image);

        cardArtDisplay.setFitHeight(image.getHeight() * 0.6);
        cardArtDisplay.setFitWidth(image.getWidth() * 0.5);

        card.getChildren().add(cardArtDisplay);
    }

    public Pane getCard() {
        return card;
    }
}
