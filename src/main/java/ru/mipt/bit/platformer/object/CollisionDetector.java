package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;

public class CollisionDetector{
    static public void startMovement(MoovableLocalObject object, GridPoint2 destinationCoordinates, Field field, float updatedTankRotation) {
        if (Boolean.FALSE.equals(field.checkIfObstacle(destinationCoordinates))) {
            object.moovableTile.setObjectMovementProgress(0f);
            object.moovableTile.setTileRotation(updatedTankRotation);
            object.moovableTile.setTileDestinationCoordinates(destinationCoordinates);
            field.setObject(destinationCoordinates, object);
        }
    }
}
