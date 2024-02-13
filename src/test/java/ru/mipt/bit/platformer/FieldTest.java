package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {
    private Field field;

    @Test
    void setTile() {
        Object objectNotObstacle = new Object();
        GridPoint2 notObstacleCoordinates = new GridPoint2(5, 5);
        field.setObject(notObstacleCoordinates, objectNotObstacle);
        assertFalse(field.checkIfObstacle(notObstacleCoordinates));

        Object objectObstacle = new Object();
        objectObstacle.setObstacle(true);
        GridPoint2 obstacleCoordinates = new GridPoint2(6, 6);
        field.setObject(obstacleCoordinates, objectObstacle);
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
        field = new Field(new Object[10][8]);
        for (int i = 0; i < 3; ++i) {
            Object object = new Object();
            object.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), object);
        }
    }

    @AfterEach
    void tearDown() {
    }
}