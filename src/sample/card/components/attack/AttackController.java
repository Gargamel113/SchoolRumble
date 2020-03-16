package sample.card.components.attack;

import sample.card.model.Card;
import sample.summoner.Summoner;

public class AttackController {
    AttackView view;
    Attack model;

    public AttackController(Attack model, AttackView view) {
        this.view = view;
        this.model = model;
        view.fontSetup(model.getAttack());
    }

    public void setAttack(int dmg) {
        model.setAttack(model.getAttack() + dmg);
        view.updateView(model.getAttack());
    }

    public int getAttack() {
        return model.getAttack();
    }

    public AttackView getView() {
        return view.getView();
    }

    public void setTranslate(double x, double y) {
        view.getView().setTranslateX(x);
        view.getView().setTranslateY(y);
    }

    public void attack(Card card) {
        card.setHealth(card.getHealth() - this.getAttack());
    }

    public void attack(Summoner summoner) {
        summoner.setHealth(summoner.getHealth() - this.getAttack());
    }
}
