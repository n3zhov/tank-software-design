package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.mipt.bit.platformer.player.Tank;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.CollisionDetector;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.object.Object;

import static org.junit.jupiter.api.Assertions.*;

class CollisionDetectorTest {

    private MoovableTile tankTile;
    private Tank playerTank;
    private Field field;


    @org.junit.jupiter.api.Test
    void startMovement() {
        CollisionDetector.startMovement(playerTank, new GridPoint2(1, 2), field, 90f);
        assertEquals(playerTank.getMoovableTile().getTileDestinationCoordinates(), new GridPoint2(1, 1));

        CollisionDetector.startMovement(playerTank, new GridPoint2(0, 1), field, -180f);
        assertEquals(playerTank.getMoovableTile().getTileDestinationCoordinates(), new GridPoint2(0, 1));
    }

    @BeforeEach
    void setUp() {
        field = new Field(new Object[10][8]);
        for (int i = 0; i < 3; ++i) {
            Object object = new Object();
            object.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), object);
        }


        tankTile = new MoovableTile(new GridPoint2(1, 1));
        playerTank = new Tank(field, tankTile);
    }

    @AfterEach
    void tearDown() {
    }
}