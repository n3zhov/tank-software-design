package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.observer.Publisher;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private Field field;

    @Test
    void setTile() {
        LocalObject localObjectNotObstacle = new LocalObject();
        GridPoint2 notObstacleCoordinates = new GridPoint2(5, 5);
        field.setObject(notObstacleCoordinates, localObjectNotObstacle);
        assertFalse(field.checkIfObstacle(notObstacleCoordinates));

        LocalObject localObjectObstacle = new LocalObject();
        localObjectObstacle.setObstacle(true);
        GridPoint2 obstacleCoordinates = new GridPoint2(6, 6);
        field.setObject(obstacleCoordinates, localObjectObstacle);
        assertTrue(field.checkIfObstacle(obstacleCoordinates));
    }

    @Test
    void deleteObstacle() {
        for (int i = 0; i < 3; ++i) {
            field.deleteObstacle(new GridPoint2(1 + i, 2 + i));
        }

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 8; ++j) {
                assertFalse(field.checkIfObstacle(new GridPoint2(i, j)));
            }
        }
    }

    @Test
    void checkIfObstacle() {
        for (int i = 0; i < 3; ++i) {
            assertTrue(field.checkIfObstacle(new GridPoint2(1 + i, 2 + i)));
        }
    }

    @BeforeEach
    void setUp() {
        field = new Field(new LocalObject[10][8], new Publisher[10][8]);
        for (int i = 0; i < 3; ++i) {
            LocalObject localObject = new LocalObject();
            localObject.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), localObject);
        }
    }

    @AfterEach
    void tearDown() {
    }
}