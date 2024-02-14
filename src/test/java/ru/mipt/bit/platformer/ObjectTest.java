package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.Object;
import ru.mipt.bit.platformer.object.Tile;

import static org.junit.jupiter.api.Assertions.*;

class ObjectTest {
    private Field field;

    private Tile treeTile;
    @org.junit.jupiter.api.Test
    void constructorTest() {
        Object object = new Object(field, treeTile, true);
        assertTrue(field.checkIfObstacle(object.getTile().getTileCoordinates()));
    }
    @BeforeEach
    void setUp() {
        field = new Field(new Object[10][8]);
        for (int i = 0; i < 3; ++i) {
            Object object = new Object();
            object.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), object);
        }
        treeTile = new Tile(new GridPoint2(1, 1));
    }

    @AfterEach
    void tearDown() {
    }
}