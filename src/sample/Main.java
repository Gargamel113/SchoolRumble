package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import sample.board.Table;
import screens.main_menu.MainMenu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    static GridPane root;
    private Stage primaryStage;


    //TESTAR TILEPANE DEM VAR GRIDPANE INNAN

    @Override
    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        primaryStage.setTitle("School Rumble");
//
//        Table table = new Table();
//        root = table.getTable();
//
//        try {
//            setBackground();
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
//
//        Scene scene = new Scene(root, 1920, 1080);
//        primaryStage.setFullScreen(true);
//
//
//        primaryStage.setScene(scene);
//        primaryStage.show();


        Game game = new Game();
        game.start(primaryStage);

//        MainMenu mainMenu = new MainMenu("School Rumble");
//        mainMenu.build();
//
//        Scene scene = new Scene(mainMenu.getMainMenu(), 1920, 1080);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    public static void setBackground() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\Robbin\\Desktop\\ART\\2x\\GB2x.png");
        Image image = new Image(inputStream);
        BackgroundImage bi = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(bi));
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static GridPane getRoot() {
        return root;
    }

}




