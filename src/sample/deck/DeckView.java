package sample.deck;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DeckView extends Pane {
    Image image;
    ImageView imageView;

    public DeckView() {
        imageView = new ImageView();
        addDeckArt();
    }

    public Pane getView() {
        return this;
    }

    private void addDeckArt() {
        importImage();

        imageView.setImage(image);
        imageView.setFitHeight(image.getHeight() * 0.75);
        imageView.setFitWidth(image.getWidth() * 0.75);
        getChildren().add(imageView);
    }

    private void importImage() {
        try {
            image = new Image(new FileInputStream("C:\\Users\\Robbin\\Desktop\\ART\\4x\\CardBack4x.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Color getColor() {
        int r = (int) (Math.random() * 255);
        int g = (int) (Math.random() * 255);
        int b = (int) (Math.random() * 255);
        return Color.rgb(r, g, b);
    }
}
