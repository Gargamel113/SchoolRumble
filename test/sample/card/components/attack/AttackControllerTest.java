package sample.card.components.attack;

import javafx.application.Platform;
import org.junit.jupiter.api.*;
import sample.card.model.Card;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AttackControllerTest {
    private AttackController acTest;
    private Card cardTest;

    @BeforeAll
    void init() {
        Platform.startup(() -> {
        });
        acTest = new AttackController(new Attack(4), new AttackView());
        cardTest = new Card(144, 192);
    }

    @Test
    void testSetAttack() {
        assertEquals(4, acTest.getAttack());
        acTest.setAttack(15);
        assertEquals(15, acTest.getAttack());
    }

    @Test
    void testGetAttack() {
        assertEquals(4, acTest.getAttack(), "Checking attack damage");
    }

    @Test
    void testGetView() {
        assertNotNull(acTest.getView());
    }

    @Test
    void testSetTranslate() {
        double x = acTest.getView().getTranslateX();
        double y = acTest.getView().getTranslateY();

        acTest.setTranslate(100, 100);

        assertEquals(x + 100, acTest.getView().getTranslateX());
        assertEquals(y + 100, acTest.getView().getTranslateY());

        assertNotEquals(x, acTest.getView().getTranslateX());
        assertNotEquals(y, acTest.getView().getTranslateY());
    }

    @Test
    void testAttack() {
        int hpBeforeTest = cardTest.getHealth();
        acTest.attack(cardTest);

        assertNotEquals(hpBeforeTest, cardTest.getHealth());
        assertEquals(cardTest.getHealth(), (hpBeforeTest - acTest.getAttack()));
    }


    @AfterAll
    void destroy() {
        acTest = null;
        cardTest = null;
        Platform.exit();
    }
}