package sample.card.model;

import javafx.scene.image.Image;
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
import sample.summoner.SummonerController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;

public class Card extends Pane {
    private CardView view;
    private MouseEvents mouseEvents;
    private HealthController healthController;
    private ManaController manaController;
    private AttackController attackController;

    private State state;
    private SummonerController reference;

    private boolean isSelected;
    private UUID uuid;

    public Card(int width, int height) {
        this.view = new CardView(width, height);
        this.isSelected = false;
        this.state = State.INHAND;
        this.uuid = UUID.randomUUID();
        this.mouseEvents = new MouseEvents();

        graphicsSetup();
    }

    public void setReference(SummonerController summoner) {
        this.reference = summoner;
    }

    public SummonerController getSummoner() {
        return reference;
    }

    //GETTERS AND SETTERS
    public void setState(State state) {
        this.state = state;
        setStyle();
    }

    public State getState() {
        return state;
    }

    public void setSelected() {
        isSelected = !isSelected;
        updateSelected();
    }



//    public HealthController getHealthController() {
//        return healthController;
//    }

    public int getHealth() {
        return healthController.getHealth();
    }

    public void setHealth(int hp) {
        healthController.setHealth(hp);
    }

    public AttackController getAttackController() {
        return attackController;
    }

    public ManaController getManaController() {
        return manaController;
    }

    public int getMana(){
        return getManaController().getMana();
    }

    public String getUUID() {
        return uuid.toString();
    }


    //GRAPHICS
    public void onField() {

        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Robbin\\Desktop\\ART\\4x\\ActivePlaceholder4x2.png"));
            view.setNewImage(image);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        healthController.setTranslate(90, 120);
        attackController.setTranslate(0, 120);

        setLife();
        setAttack();
        setStyle();
    }

    public void setLife() {
        view.getCard().getChildren().add(healthController.getView());
    }

    public void setMana() {
        view.getCard().getChildren().add(manaController.getView());
    }

    public void setAttack() {
        view.getCard().getChildren().add(attackController.getView());
    }

    private void setStyle() {
        switch (this.state) {
            case SLEEP:
                view.sleep();
                break;
            case ACTIVE:
                view.active();
                break;
        }
    }

    public void updateSelected() {
        if (isSelected) {
            view.highLight();
        } else {
            view.deHighLight();
        }
    }

    private void graphicsSetup() {
        int rHealt = (int) (Math.random() * 10) + 1;
        int rAttack = (int) (Math.random() * 10) + 1;
        int rMana = (rHealt + rAttack) / 6;

        this.healthController = new HealthController(new Health(rHealt), new HealthView());
        this.manaController = new ManaController(new Mana(rMana), new ManaView());
        this.attackController = new AttackController(new Attack(rAttack), new AttackView());

        setLife();
        setMana();
        setAttack();

        healthController.setTranslate(138, 210);
        attackController.setTranslate(0, 210);
        manaController.setTranslate(4, 4);

        getChildren().add(view.getCard());
        this.setOnMousePressed(mouseEvents.pressed);
        this.setOnMouseEntered(mouseEvents.onMouseHover);
        this.setOnMouseExited(mouseEvents.onMouseHoverExit);
    }

    public void mouseEventOn() {
        this.setOnMousePressed(mouseEvents.pressed);
    }

    public void mouseEventOff() {
        this.setOnMousePressed(mouseEvents.sleep);
    }
}
