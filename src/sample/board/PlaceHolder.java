package sample.board;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class PlaceHolder extends TilePane {
    private int colSpan, rowSpan, col, row;
    private String id;

    private PlaceHolder(String rgba) {
        this.setPrefWidth(Screen.getPrimary().getBounds().getWidth() / 2);
        this.setPrefHeight(190);
        this.setBorder(
                new Border(
                        new BorderStroke(
                                Color.BLACK, BorderStrokeStyle.SOLID,
                                null, new BorderWidths(4))));

        this.setStyle("-fx-background-color: rgba(" + rgba + ");");
    }

    public void setStyle(GridPane pane, int row, int col) {
        pane.setColumnSpan(this, colSpan);
        pane.setRowSpan(this, rowSpan);

        pane.add(this, col, row);
        this.setId(id);

        this.setHgap(5);
        this.setAlignment(Pos.CENTER);
    }

    public static class PlaceHolderBuilder {
        private int colSpan, rowSpan, col, row;
        private String rgba, id;

        public PlaceHolderBuilder(String id) {
            this.id = id;
        }

        public PlaceHolderBuilder color(String rgba) {
            this.rgba = rgba;
            return this;
        }

        public PlaceHolderBuilder colSpan(int colSpan) {
            this.colSpan = colSpan;
            return this;
        }

        public PlaceHolderBuilder rowSpan(int rowSpan) {
            this.rowSpan = rowSpan;
            return this;
        }

        public PlaceHolderBuilder row(int row) {
            this.row = row;
            return this;
        }

        public PlaceHolderBuilder col(int col) {
            this.col = col;
            return this;
        }

        public PlaceHolder build() {
            PlaceHolder placeHolder = new PlaceHolder(this.rgba);
            placeHolder.colSpan = this.colSpan;
            placeHolder.rowSpan = this.rowSpan;
            placeHolder.id = this.id;
            placeHolder.row = this.row;
            placeHolder.col = this.col;
            return placeHolder;
        }
    }


}
