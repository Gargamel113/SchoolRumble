package sample.board;

import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import sample.deck.Deck;
import sample.deck.DeckController;
import sample.deck.DeckView;
import sample.summoner.Summoner;
import sample.summoner.SummonerController;
import sample.summoner.SummonerView;


public class Table {
    private PlaceHolder playerGameBoard;
    private PlaceHolder playerCardHolder;

    private PlaceHolder enemyGameBoard;
    private PlaceHolder enemyCardHolder;

    private PlaceHolder endTurn;

    private GridPane table;


    //TODO remove test variables and change them to be added to the table;
    DeckController deckPlayer = new DeckController(new Deck(), new DeckView());
    DeckController deckEnemy = new DeckController(new Deck(), new DeckView());

    SummonerController summonerPlayer = new SummonerController(new Summoner("Robbin", deckPlayer), new SummonerView());
    SummonerController summonerEnemy = new SummonerController(new Summoner("Sofie", deckEnemy), new SummonerView());

    Match match;

    EndTurnButton etButton = new EndTurnButton();

    public Table() {
        deckPlayer.initDeck();
        deckEnemy.initDeck();
        tableInit();
        match = new Match(summonerPlayer, summonerEnemy);
    }

    public GridPane getTable() {
        return table;
    }

    private void tableInit() {
        table = new GridPane();
        table.setGridLinesVisible(false);

        double width = Screen.getPrimary().getBounds().getWidth();
        double height = Screen.getPrimary().getBounds().getHeight();

        for (int i = 0; i < 30; i++) {
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(width / 30);
            col.setHgrow(Priority.NEVER);
            table.getColumnConstraints().add(col);
        }

        for (int i = 0; i < 30; i++) {
            RowConstraints rows = new RowConstraints();
            rows.setPercentHeight(height / 30);
            rows.setVgrow(Priority.NEVER);
            table.getRowConstraints().add(rows);
        }

//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < 20; j++) {
//                table.add(new Label(i + ", " + j), j, i);
//            }
//        }

        initTablePieces();
    }

    private void initTablePieces() {
        placeHolderSetup();

        playerCardHolder.toBack();
        playerGameBoard.toBack();
        enemyGameBoard.toBack();
        enemyCardHolder.toBack();

        addCard();
    }


    //TEMPORARY METHOEDS
    public void addCard() {
        for (int i = 0; i < 10; i++) {
            playerCardHolder.getChildren().add(summonerPlayer.drawCard());
        }

        for (int i = 0; i < 10; i++) {
            enemyCardHolder.getChildren().add(summonerEnemy.drawCard());
        }
    }

    private void placeHolderSetup() {
        //PlAYER SUMMONER
        PlaceHolder playerSummoner = new PlaceHolder.PlaceHolderBuilder("playerSummonerPH")
                .color("100, 200, 200, 0.5")
                .colSpan(5)
                .rowSpan(7)
                .build();
        playerSummoner.setStyle(table, 23, 11);
        createSummonerArt(playerSummoner);

        //PLAYER GAME BOARD
        playerGameBoard = new PlaceHolder.PlaceHolderBuilder("playerGameBoardPH")
                .color("100, 100, 200, 0.4")
                .colSpan(24)
                .rowSpan(5)
                .build();
        playerGameBoard.setStyle(table, 17, 3);


        //PLAYER CARD HOLDER
        playerCardHolder = new PlaceHolder.PlaceHolderBuilder("playerCardHolderPH")
                .color("200, 100, 100, 0.4")
                .colSpan(11)
                .rowSpan(4)
                .build();
        playerCardHolder.setStyle(table, 24, 16);


        //PLAYER DECK
        PlaceHolder playerDeck = new PlaceHolder.PlaceHolderBuilder("playerDeckPH")
                .color("50, 200, 50, 0.4")
                .colSpan(1)
                .rowSpan(4)
                .build();
        playerDeck.setStyle(table, 23, 27);
        addDeckArt(playerDeck);


        //ENEMY SUMMONER
        PlaceHolder enemySummoner = new PlaceHolder.PlaceHolderBuilder("enemySummonerPH")
                .color("100, 200, 200, 0.5")
                .colSpan(5)
                .rowSpan(7)
                .build();
        enemySummoner.setStyle(table, 0, 14);
        createSummonerArt(enemySummoner);

        //ENEMY GAME BOARD
        enemyGameBoard = new PlaceHolder.PlaceHolderBuilder("enemyGameBoardPH")
                .color("100, 100, 200, 0.4")
                .colSpan(24)
                .rowSpan(5)
                .build();
        enemyGameBoard.setStyle(table, 8, 3);

        //ENEMY CARD HOLDER
        enemyCardHolder = new PlaceHolder.PlaceHolderBuilder("enemyCardHolderPH")
                .color("200, 100, 100, 0.4")
                .colSpan(11)
                .rowSpan(5)
                .build();
        enemyCardHolder.setStyle(table, 1, 3);

        //ENEMY DECK
        PlaceHolder enemyDeck = new PlaceHolder.PlaceHolderBuilder("enemyDeckPH")
                .color("50, 200, 50, 0.4")
                .colSpan(1)
                .rowSpan(4)
                .build();
        enemyDeck.setStyle(table, 3, 0);
        addDeckArt(enemyDeck);


        endTurn = new PlaceHolder.PlaceHolderBuilder("endTurnPH")
                .color("100, 100, 100, 0.4")
                .colSpan(2)
                .rowSpan(2)
                .build();
        endTurn.setStyle(table, 14, 27);
        createButtonArt(endTurn);

        fixProportions();
    }

    private void fixProportions() {
        playerGameBoard.setHgap(20);
        enemyGameBoard.setHgap(20);

        playerCardHolder.setHgap(-100);
        enemyCardHolder.setHgap(-100);
    }

    private void addDeckArt(TilePane tilePane) {
        Pane deck = tilePane.getId().equals("playerDeckPH") ? summonerPlayer.getCardView() : summonerEnemy.getCardView();
        tilePane.getChildren().add(deck);
    }

    private void createSummonerArt(TilePane tilePane) {
        boolean isPlayer = tilePane.getId().equals("playerSummonerPH");
        tilePane.getChildren().add((isPlayer ? summonerPlayer : summonerEnemy).getView());
        tilePane.setAlignment(isPlayer ? Pos.TOP_RIGHT : Pos.BOTTOM_LEFT);
    }

    private void createButtonArt(TilePane tilePane) {
        tilePane.getChildren().add(etButton.getEndTurnButton());
    }

    public PlaceHolder getEndTurn() {
        return endTurn;
    }

    public Match getMatch() {
        return match;
    }

    public SummonerController getSummonerPlayer() {
        return summonerPlayer;
    }

    public SummonerController getSummonerEnemy() {
        return summonerEnemy;
    }
}
