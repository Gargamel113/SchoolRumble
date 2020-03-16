package sample.deck;

import javafx.scene.layout.Pane;
import sample.card.model.Card;
import sample.summoner.SummonerController;

import java.util.ArrayList;
import java.util.List;

public class DeckController {
    Deck model;
    DeckView view;
    List<Card> usedSet;

    //Reference of summoner
    SummonerController summoner;

    public DeckController(Deck model, DeckView view) {
        this.model = model;
        this.view = view;
        this.usedSet = new ArrayList<>();
    }

    public void initDeck() {
        for (int i = 0; i < 30; i++) {
            Card card = new Card(model.getW(), model.getH());
            card.setId("Card");
            card.setReference(this.summoner);
            model.addCard(card);
        }
    }

    public void setSummoner(SummonerController summoner) {
        this.summoner = summoner;
    }

    public List<Card> getDeck() {
        return model.getDeck();
    }

    public List<Card> getUsedSet() {
        return usedSet;
    }

    public Card getCard() {
        Card card = model.getDeck().remove(0);
        usedSet.add(card);
        return card;
    }

    public Pane getView() {
        return view.getView();
    }
}