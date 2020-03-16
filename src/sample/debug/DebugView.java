package sample.debug;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DebugView {
    static double mouseX, mouseY, sceneX, sceneY, cardX, cardY;

    static VBox mouseView, sceneView, cardView;

    public DebugView() {
        mouseView = new VBox();
        sceneView = new VBox();
        cardView = new VBox();

        Label l = new Label("mouseX : " + mouseX);
        Label l2 = new Label("mouseY : " + mouseY);
        Label l3 = new Label("sceneX : " + sceneX);
        Label l4 = new Label("sceneY : " + sceneY);
        Label l5 = new Label("cardX : " + cardX);
        Label l6 = new Label("cardY : " + cardY);

        mouseView.getChildren().addAll(l, l2);
        sceneView.getChildren().addAll(l3, l4);
        cardView.getChildren().addAll(l5, l6);
    }

    public static void setMouseX(double mouseX) {
        DebugView.mouseX = mouseX;
        mouseView.getChildren().clear();
        mouseView.getChildren().add(new Label("mouseX : " + mouseX));
    }

    public static void setMouseY(double mouseY) {
        DebugView.mouseY = mouseY;
    }

    public static void setSceneX(double sceneX) {
        DebugView.sceneX = sceneX;
    }

    public static void setSceneY(double sceneY) {
        DebugView.sceneY = sceneY;
    }

    public static void setCardX(double cardX) {
        DebugView.cardX = cardX;
    }

    public static void setCardY(double cardY) {
        DebugView.cardY = cardY;
    }

    public VBox getMouseView() {
        return mouseView;
    }

    public VBox getSceneView() {
        return sceneView;
    }

    public VBox getCardView() {
        return cardView;
    }
}
