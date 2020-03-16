package sample.deck;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sample.card.model.Card;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeckControllerTest {
    DeckController deckControllerTest = new DeckController(new Deck(), new DeckView());

    @BeforeAll
    void init() {
        Platform.startup(() -> {
        });
        deckControllerTest.initDeck();
    }

    @Test
    void initDeck() {
        assertNotNull(deckControllerTest.getDeck());
        assertEquals(28, deckControllerTest.getDeck().size());
    }

    @Test
    void getDeck() {
        assertNotNull(deckControllerTest.getDeck());
    }

    @Test
    void getUsedSet() {
        assertNotEquals(deckControllerTest.getUsedSet(), deckControllerTest.getDeck());
        assertEquals(2, deckControllerTest.getUsedSet().size());
    }

    @Test
    void getCard() {
        assertEquals(Card.class, deckControllerTest.getCard().getClass());
        assertNotNull(deckControllerTest.getCard());
    }

    @Test
    void getView() {
        assertNotNull(deckControllerTest.getView());
    }
}