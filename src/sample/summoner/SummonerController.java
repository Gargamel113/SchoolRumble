package sample.summoner;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import sample.board.GameObject;
import sample.card.components.attack.AttackController;
import sample.card.model.Card;
import java.util.stream.Stream;

public class SummonerController {
    private Summoner model;
    private SummonerView view;

    public SummonerController(Summoner model, SummonerView view) {
        this.model = model;
        this.view = view;
        addLife();
        addMana();
        model.setDeckReference(this);
        view.setOnMousePressed(model.getMouseEvent().summonerEvent);
        view.setController(this);
    }

    public Summoner getModel() {
        return model;
    }

    public Pane getView() {
        return view.getView();
    }

    public String getName() {
        return model.getName();
    }

    public int getHealth() {
        return model.getHealth();
    }

    public void setHealth(int dmg) {
        model.setHealth(dmg);
    }

    public int getMana() {
        return model.getMana().getMana();
    }

    public void setMana(int mana) {
        model.getMana().setMana(mana);
    }

    public void resetMana(int mana) {
        model.getMana().resetMana(mana);
    }

    public AttackController getAttack(){
        return model.getAttack();
    }
    //LÄGG TILL METOD HÄR FÖR ATT DRA NYTT KORT!!
    public void drawCardFromDeck() {
        String parentId = model.getDeckView().getParent().getId();
        TilePane gameBoard = parentId.equals("playerDeckPH") ? GameObject.getPlayerCH() : GameObject.getOpponentCH();

        if (gameBoard.getChildren().size() < 10 && model.getDeck().size() > 0) {
            gameBoard.getChildren().add(model.drawCard());
        }
    }

    public Card drawCard() {
        return model.drawCard();
    }

    public Pane getCardView() {
        return model.getDeckView();
    }

    public void setCardsActive() {
        Stream.concat(model.getDeck().stream(), model.getUsedSet().stream())
                .forEach(Card::mouseEventOn);
    }

    public void setCardsInactive() {
        Stream.concat(model.getDeck().stream(), model.getUsedSet().stream())
                .forEach(Card::mouseEventOff);
    }

    private void addLife() {
        view.addChild(model.getHpView());
    }

    private void addMana() {
        view.addChild(model.getMana().getView());
        model.getMana().setTranslate(160, 0);
    }
}
