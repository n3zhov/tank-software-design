package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.mipt.bit.platformer.object.*;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.player.Tank;
import ru.mipt.bit.platformer.level.Field;

import static org.junit.jupiter.api.Assertions.*;

class TankTest {
    private MoovableTile tankTile;
    private Tank playerTank;
    private Field field;
    @BeforeEach
    void setUp() {
        field = new Field(new LocalObject[10][8]);
        for (int i = 0; i < 3; ++i) {
            LocalObject localObject = new LocalObject();
            localObject.setObstacle(true);
            field.setObject(new GridPoint2(1 + i, 2 + i), localObject);
        }


        tankTile = new MoovableTile(new GridPoint2(1, 1));
        playerTank = new Tank(field, tankTile);
    }

    @Test
    void moveTo() {
        Command command = new Command();
        command.setAction(TypeOfAction.MoveAction);

        command.setDirection(Direction.UP);
        playerTank.execute(command, field);
        assertEquals(playerTank.getMoovableTile().getTileDestinationCoordinates(), new GridPoint2(1, 1));
        assertEquals(playerTank.getMoovableTile().getTileRotation(), 0f);

        command.setDirection(Direction.LEFT);
        playerTank.execute(command, field);
        assertEquals(playerTank.getMoovableTile().getTileDestinationCoordinates(), new GridPoint2(0, 1));
        assertEquals(playerTank.getMoovableTile().getTileRotation(), -180f);
    }
}