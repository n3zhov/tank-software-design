package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.MoovableLocalObject;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.object.LocalObject;

import static org.junit.jupiter.api.Assertions.*;

class MoovableLocalObjectTest {

    private Field field;

    private MoovableTile tankTile;
    @org.junit.jupiter.api.Test
    void constructorTest() {
        MoovableLocalObject object = new MoovableLocalObject(field, tankTile, true);
        assertTrue(field.checkIfObstacle(object.getMoovableTile().getTileCoordinates()));
    }
    @BeforeEach
    void setUp() {
        field = new Field(new LocalObject[10][8]);
        for (int i = 0; i < 3; ++i) {
            LocalObject localObject = new LocalObject();
            localObject.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), localObject);
        }
        tankTile = new MoovableTile(new GridPoint2(1, 1));
    }

    @AfterEach
    void tearDown() {
    }
}