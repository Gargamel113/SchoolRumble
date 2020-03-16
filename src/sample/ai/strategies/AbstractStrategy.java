package sample.ai.strategies;

import sample.card.model.Card;
import java.util.List;

public abstract class AbstractStrategy {
    List<Card> humanCardsOB;
    List<Card> aiCardsOB;

    public AbstractStrategy(List<Card> list, List<Card> aiList) {
        this.humanCardsOB = list;
        this.aiCardsOB = aiList;
    }

    public abstract int whatToAttack();

    public List<Card> getHumanCards() {
        return humanCardsOB;
    }
}
