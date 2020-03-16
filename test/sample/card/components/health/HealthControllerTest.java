package sample.card.components.health;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import sample.card.components.attack.Attack;
import sample.card.components.attack.AttackController;
import sample.card.components.attack.AttackView;
import sample.card.model.Card;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HealthControllerTest {
    private HealthController healthControllerTest;

    @BeforeAll
    void init() {
        Platform.startup(() -> {
        });
        healthControllerTest = new HealthController(new Health(5), new HealthView());
    }


    @Test
    void healthCheck() {
        assertAll("Get And Set HP",
                () -> assertNotNull(healthControllerTest),
                () -> assertEquals(5, healthControllerTest.getHealth()),
                () -> healthControllerTest.setHealth(10),
                () -> assertEquals(10, healthControllerTest.getHealth())
        );
    }

    @Test
    void getView() {
        assertNotNull(healthControllerTest.getView());
    }

    @Test
    void setTranslate() {
        double x = healthControllerTest.getView().getTranslateX();
        double y = healthControllerTest.getView().getTranslateY();

        healthControllerTest.setTranslate(100, 100);

        assertEquals(x + 100, healthControllerTest.getView().getTranslateX());
        assertEquals(y + 100, healthControllerTest.getView().getTranslateY());

        assertNotEquals(x, healthControllerTest.getView().getTranslateX());
        assertNotEquals(y, healthControllerTest.getView().getTranslateY());
    }
}