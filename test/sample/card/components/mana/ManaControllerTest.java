package sample.card.components.mana;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ManaControllerTest {
    private ManaController manaControllerTest;

    @BeforeAll
    void init() {
        Platform.startup(() -> {
        });
        manaControllerTest = new ManaController(new Mana(5), new ManaView());
    }

    @Test
    void healthCheck() {
        assertAll("Get And Set HP",
                () -> assertNotNull(manaControllerTest),
                () -> assertEquals(5, manaControllerTest.getMana()),
                () -> manaControllerTest.setMana(5),
                () -> assertEquals(0, manaControllerTest.getMana()),
                () -> manaControllerTest.resetMana(10),
                () -> assertEquals(10, manaControllerTest.getMana())
        );
    }

    @Test
    void getView() {
        assertNotNull(manaControllerTest.getView());
    }

    @Test
    void setTranslate() {
        double x = manaControllerTest.getView().getTranslateX();
        double y = manaControllerTest.getView().getTranslateY();

        manaControllerTest.setTranslate(100, 100);

        assertEquals(x + 100, manaControllerTest.getView().getTranslateX());
        assertEquals(y + 100, manaControllerTest.getView().getTranslateY());

        assertNotEquals(x, manaControllerTest.getView().getTranslateX());
        assertNotEquals(y, manaControllerTest.getView().getTranslateY());
    }
}