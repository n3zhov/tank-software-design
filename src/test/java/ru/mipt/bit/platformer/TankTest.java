package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.player.Tank;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.Direction;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.object.Object;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {
    private MoovableTile tankTile;
    private Tank playerTank;
    private Field field;
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

    @Test
    void moveTo() {
        playerTank.moveTo(Direction.UP, field);
        assertEquals(playerTank.getMoovableTile().getTileDestinationCoordinates(), new GridPoint2(1, 1));
        assertEquals(playerTank.getMoovableTile().getTileRotation(), 0f);

        playerTank.moveTo(Direction.LEFT, field);
        assertEquals(playerTank.getMoovableTile().getTileDestinationCoordinates(), new GridPoint2(0, 1));
        assertEquals(playerTank.getMoovableTile().getTileRotation(), -180f);
    }
}