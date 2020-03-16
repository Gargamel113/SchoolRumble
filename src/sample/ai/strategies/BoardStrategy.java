package sample.ai.strategies;

import sample.card.model.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardStrategy extends AbstractStrategy {

    Map<Integer, AbstractStrategy> threatLevel;

    public BoardStrategy(List<Card> humanCards, List<Card> aiCards) {
        super(humanCards, aiCards);
    }

    @Override
    public int whatToAttack() {
        if (humanCardsOB.size() > 0) {
            if (humanCardsOB.size() > aiCardsOB.size()) {
                return (int) (Math.random() * humanCardsOB.size());
            } else {
                return -1;
            }
        }
        return -1;
    }


    //METHOD TO DESCIDE TO ATTACK CARDS FOR HEALTH OR ATTACK
    //=================================
    /**
     * Calculate total health on board
     * @return
     */
    private List<Card> health() {
        List<Card> healthCards = new ArrayList<>(humanCardsOB);
        if (healthCards.size() > 1){
            sort(healthCards);
        }


        return null;
    }

    /**
     * Calcualte total attack on board
     * @return
     */
    private List<Card> attack() {

        return null;
    }

    //TODO FIX BUBBLESORT FOR AI HEAD
    private void sort(List<Card> list){
        Card c1, c2;

        for(int i = 0; i < list.size(); i++){
            c1 = list.get(i);
            c2 = list.get(i+1);

        }
    }
    //=================================


    /*
    Threat level 1-10;

    1-10


    index 1 = ?????
     */
}
