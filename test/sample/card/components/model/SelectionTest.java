package sample.card.components.model;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sample.card.model.Card;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SelectionTest {
    Selection selectionTest = Selection.INSTANCE;

    @BeforeAll
    void init() {
        Platform.startup(() -> {});
    }

    @Test
    void newTurn() {
        selectionTest.newTurn();
        assertNull(selectionTest.getCard(0));
        assertNull(selectionTest.getCard(1));
    }

    @Test
    void getCard() {
        assertNotNull(selectionTest.getCard(0));
    }

    @Test
    void selectCard() {
        selectionTest.selectCard(new Card(1,1));
    }
}