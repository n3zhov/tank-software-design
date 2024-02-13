package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.object.Tile;

import static org.junit.jupiter.api.Assertions.*;

class LocalObjectTest {
    private Field field;

    private Tile treeTile;
    @org.junit.jupiter.api.Test
    void constructorTest() {
        LocalObject localObject = new LocalObject(field, treeTile, true);
        assertTrue(field.checkIfObstacle(localObject.getTile().getTileCoordinates()));
    }
    @BeforeEach
    void setUp() {
        field = new Field(new LocalObject[10][8]);
        for (int i = 0; i < 3; ++i) {
            LocalObject localObject = new LocalObject();
            localObject.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), localObject);
        }
        treeTile = new Tile(new GridPoint2(1, 1));
    }

    @AfterEach
    void tearDown() {
    }
}