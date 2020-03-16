package sample.summoner;

import javafx.scene.layout.Pane;
import sample.card.components.attack.Attack;
import sample.card.components.attack.AttackController;
import sample.card.components.attack.AttackView;
import sample.card.components.health.Health;
import sample.card.components.health.HealthController;
import sample.card.components.health.HealthView;
import sample.card.components.mana.Mana;
import sample.card.components.mana.ManaController;
import sample.card.components.mana.ManaView;
import sample.card.components.model.MouseEvents;
import sample.card.model.Card;
import sample.deck.DeckController;

import java.util.List;

public class Summoner {
    private String name;
    private DeckController deck;
    private HealthController health;
    private ManaController mana;
    private AttackController attack;
    private MouseEvents mouseEvents;

    public Summoner(String name, DeckController deck) {
        this.name = name;
        this.deck = deck;
        this.health = new HealthController(new Health(40), new HealthView());
        this.mana = new ManaController(new Mana(3), new ManaView());
        this.attack = new AttackController(new Attack(0), new AttackView());
        this.mouseEvents = new MouseEvents();
    }

    public String getName() {
        return name;
    }

    public void setDeckReference(SummonerController summonerController) {
        deck.setSummoner(summonerController);
    }

    public Pane getDeckView() {
        return deck.getView();
    }

    public Card drawCard() {
        return deck.getCard();
    }

    public void setDeck(DeckController deck) {
        this.deck = deck;
    }

    public List<Card> getDeck() {
        return deck.getDeck();
    }

    public List<Card> getUsedSet() {
        return deck.getUsedSet();
    }

    public int getHealth() {
        return health.getHealth();
    }

    public HealthView getHpView() {
        return health.getView();
    }

    public void setHealth(int dmg) {
        health.setHealth(dmg);
    }

    public ManaController getMana() {
        return mana;
    }

    public MouseEvents getMouseEvent() {
        return mouseEvents;
    }

    public AttackController getAttack() {
        return attack;
    }

}
