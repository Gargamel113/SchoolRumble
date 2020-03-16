package sample.deck;

import javafx.application.Platform;
import org.junit.jupiter.api.*;
import sample.card.model.Card;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeckTest {
    Deck deckTest = new Deck();

    @BeforeAll
    void init() {
        Platform.startup(() -> {
        });
    }

    @Test
    void testAddCard() {
        assertEquals(0, deckTest.getDeck().size());
        deckTest.addCard(new Card(1,1));
        assertEquals(1, deckTest.getDeck().size());
        assertTrue(deckTest.getDeck().add(new Card(1,1)));
    }

    @Test
    void testGetCard() {
        assertNotNull(deckTest.getCard(1));
        assertNotEquals(new Card(1,1), deckTest.getCard(1));
    }

    @Test
    void testGetDeck(){
        List<Card> list = new ArrayList<>();

        assertNotNull(deckTest.getDeck());
        assertEquals(deckTest.getDeck(), deckTest.getDeck());
        assertEquals(list.getClass(), deckTest.getDeck().getClass());
    }

    @AfterAll
    void delete() {
        Platform.exit();
    }
}