package sample.card.model;

import javafx.application.Platform;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sample.deck.Deck;
import sample.deck.DeckController;
import sample.deck.DeckView;
import sample.summoner.Summoner;
import sample.summoner.SummonerController;
import sample.summoner.SummonerView;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CardTest {
    private Card cardTest;
    private DeckController deckController;
    private SummonerController reference;

    @BeforeAll
    void init() {
        Platform.startup(() -> {
        });
        deckController = new DeckController(new Deck(), new DeckView());
        reference = new SummonerController(new Summoner("Rob", deckController), new SummonerView());
        cardTest = new Card(144, 192);
    }

    @Test
    void testSetAndGetReference() {
        assertAll("Get And Set SummonerReference",
                () -> assertNull(cardTest.getSummoner()),
                () -> cardTest.setReference(reference),
                () -> assertNotNull(cardTest.getSummoner())
        );
    }

    @Test
    void testState() {
        assertAll("Get And Set State",
                () -> assertEquals(State.INHAND, cardTest.getState()),
                () -> cardTest.setState(State.ACTIVE),
                () -> assertEquals(State.ACTIVE, cardTest.getState())
        );
    }


    @Test
    void testHealth() {
        assertAll("Get And Set Health",
                () -> cardTest.setHealth(10),
                () -> assertEquals(10, cardTest.getHealth()),
                () -> cardTest.setHealth(5),
                () -> assertEquals(5, cardTest.getHealth())
        );
    }

    @Test
    void getAttackController() {
        Card temp = new Card(1, 1);
        int hpBefore = temp.getHealth();
        assertAll("Attack Test",
                () -> assertNotNull(cardTest.getAttackController()),
                () -> cardTest.getAttackController().attack(temp),
                () -> assertNotEquals(hpBefore, temp.getHealth())
        );
    }

    @Test
    void getUUID() {
        Card temp = new Card(1, 1);
        assertNotNull(cardTest.getUUID());
        assertNotEquals(temp.getUUID(), cardTest.getUUID());
    }

    @AfterAll
    void delete() {
        Platform.exit();
    }
}