package sample.card.components.mana;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManaTest {
    Mana ManaTest = new Mana(5);

    @Test
    void testMane() {
        assertAll("Get And Set MANA",
                () -> assertNotNull(ManaTest),
                () -> assertEquals(5, ManaTest.getMana()),
                () -> ManaTest.setMana(10),
                () -> assertEquals(10, ManaTest.getMana())
        );
    }
}