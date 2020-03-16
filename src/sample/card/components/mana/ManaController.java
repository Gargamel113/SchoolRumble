package sample.card.components.mana;

public class ManaController {
    ManaView view;
    Mana model;

    public ManaController(Mana model, ManaView view) {
        this.view = view;
        this.model = model;
        view.fontSetup(model.getMana());
    }

    public void setMana(int mana) {
        model.setMana(model.getMana() - mana);
        view.updateView(model.getMana());
    }

    public void resetMana(int mana){
        model.setMana(mana);
        view.updateView(model.getMana());
    }

    public int getMana() {
        return model.getMana();
    }

    public ManaView getView() {
        return view.getView();
    }

    public void setTranslate(double x, double y) {
        view.getView().setTranslateX(x);
        view.getView().setTranslateY(y);
    }
}
