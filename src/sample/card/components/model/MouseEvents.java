package sample.card.components.model;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.Screen;
import javafx.util.Duration;
import sample.board.GameObject;
import sample.card.model.Card;
import sample.card.model.State;
import sample.summoner.Summoner;
import sample.summoner.SummonerController;
import sample.summoner.SummonerView;

public class MouseEvents {
    double zPosition;
    static Card reference;

    Selection selection = Selection.INSTANCE;


    public EventHandler<MouseEvent> summonerEvent = event -> {
        SummonerView summonerView = (SummonerView) event.getSource();
        SummonerController summoner = summonerView.getController();

        if (summoner != null) {
            if (selection.getCard(0) != null) {
                attack(selection.getCard(0), summoner.getModel());
                selection.newTurn();
            }
        }
    };

    public EventHandler<MouseEvent> sleep = event -> {
        Card card = (Card) event.getSource();
        if (selection.getCard(0) != null) {
            selection.selectCard(card);
            if (!card.getState().equals(State.INHAND) && selection.getCard(1) != null) {
                attack(selection.getCard(0), selection.getCard(1));
                cardUsed(selection.getCard(0));
                selection.newTurn();
            }

        }
    };

    public EventHandler<MouseEvent> pressed = event -> {
        Card card = (Card) event.getSource();

        switch (card.getState()) {
            case INHAND:
                addToBattlefield(card);
                break;
            case SLEEP:

                break;
            case ACTIVE:
                selectCard(card);
                break;
        }
    };


    private void addToBattlefield(Card card) {
        if (hasMana(card)) {
            selection.addToBattlefield(card);
        } else {
            System.out.println("NOT ENOUGH MANA");
        }
    }

    private void selectCard(Card card) {
        if (card.getState().equals(State.ACTIVE)) {
            selection.selectCard(card);
        } else {
            System.out.println("NOT ACTIVE");
        }
    }

    private boolean hasMana(Card card) {
        return (card.getSummoner().getMana() - card.getManaController().getMana()) >= 0;
    }

    private void cardUsed(Card card) {
        card.setState(State.SLEEP);
    }

    public EventHandler<MouseEvent> onMouseHover = event -> {
        if (!event.getButton().equals(MouseButton.PRIMARY)) {
            Card card = (Card) event.getSource();

            zPosition = card.getViewOrder();
            card.viewOrderProperty().set(zPosition - 5);

//            orgScaleX = node.getScaleX();
//            orgScaleY = node.getScaleY();
//
//            node.setScaleX(orgScaleX * 1.5);
//            node.setScaleY(orgScaleY * 1.5);
        }
    };

    public EventHandler<MouseEvent> onMouseHoverExit = event -> {
        Card card = (Card) event.getSource();
        card.viewOrderProperty().set(zPosition);
//        node.setScaleX(orgScaleX);
//        node.setScaleY(orgScaleY);

//        node.setTranslateY(orgTranslateY);
    };

    private void attack(Card attacker, Card target) {
        animate(attacker, target);

        attacker.setSelected();
        attacker.setState(State.SLEEP);
        reference = null;
    }

    private void attack(Card attacker, Summoner target) {
        attacker.getAttackController().attack(target);

        if (target.getHealth() <= 0) {
            System.out.println("SUMMONER IS DEAD");
        }

        if (attacker.getHealth() <= 0) {
            removeDead(attacker);
        }

        attacker.setSelected();
        attacker.setState(State.SLEEP);
        reference = null;
    }

    private void removeDead(Card target) {
        try {
            TilePane tile = (TilePane) target.getParent();
            tile.getChildren().remove(target);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    private void animate(Card attacker, Card target) {
        PathTransition pt = new PathTransition();
        Path path = new Path();

        double lenX = target.getLayoutX() - attacker.getLayoutX() + 60;
        double lenY = target.getParent().getLayoutY() - attacker.getParent().getLayoutY() + 80;

        path.getElements().add(new MoveTo(61, 80));
        path.getElements().add(new LineTo(lenX, lenY));


        pt.setDuration(Duration.millis(200));
        pt.setDelay(Duration.millis(300));
        pt.setPath(path);
        pt.setNode(attacker);
        pt.setCycleCount(2);
        pt.setAutoReverse(true);
        pt.playFromStart();

        pt.setOnFinished(event -> {
            attacker.getAttackController().attack(target);
            target.getAttackController().attack(attacker);

            if (target.getHealth() <= 0) {
                removeDead(target);
            }
            if (attacker.getHealth() <= 0) {
                removeDead(attacker);
            }
        });
    }

}

//    private void attack(Node attacker, Node target) {
//        Card cAttack = null;
//        Card cTarget = null;
//        SummonerController sAttack = null;
//        SummonerController sTarget = null;
//
//        if (isCard(attacker)) {
//            cAttack = convertToCard(attacker);
//        } else {
//            sAttack = convertToSummoner(attacker).getController();
//        }
//
//        if ((isCard(target))) {
//            cTarget = convertToCard(attacker);
//        } else {
//            sTarget = convertToSummoner(attacker).getController();
//        }
//
//        if (cAttack != null) {
//            if (cTarget != null) {
//                cAttack.getAttackController().attack(cTarget);
//            } else {
//                cAttack.getAttackController().attack(sTarget.getModel());
//            }
//        } else {
//            if (cTarget != null) {
//                sAttack.getAttack().attack(cTarget);
//            } else {
//                sAttack.getAttack().attack(sTarget.getModel());
//            }
//        }
//
//
//    }

//    private boolean isCard(Node node) {
//        try {
//            Card temp = (Card) node;
//            return true;
//        } catch (ClassCastException e) {
//            return false;
//        }
//    }
//
//    private Card convertToCard(Node node) {
//        return (Card) node;
//    }
//
//    private SummonerView convertToSummoner(Node node) {
//        return (SummonerView) node;
//
//    }