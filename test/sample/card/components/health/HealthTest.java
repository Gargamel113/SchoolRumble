package sample.card.components.health;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthTest {
    Health healthTest = new Health(5);

    @Test
    void testHp() {
        assertAll("Get And Set HP",
                () -> assertNotNull(healthTest),
                () -> assertEquals(5, healthTest.getHp()),
                () -> healthTest.setHp(10),
                () -> assertEquals(10, healthTest.getHp())
        );
    }
}