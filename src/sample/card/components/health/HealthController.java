package sample.card.components.health;

public class HealthController {
    HealthView view;
    Health model;

    public HealthController(Health model, HealthView view){
        this.view = view;
        this.model = model;
        view.fontSetup(model.getHp());
    }

    public void setHealth(int health){
        model.setHp(health);
        view.updateView(model.getHp());
    }

    public int getHealth(){
        return model.getHp();
    }

    public HealthView getView(){
        return view.getView();
    }

    public void setTranslate(double x, double y){
        view.getView().setTranslateX(x);
        view.getView().setTranslateY(y);
    }
}
