package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class CollisionDetector{
    static public void startMovement(MoovableObject object, GridPoint2 destinationCoordinates, Field field, float updatedTankRotation) {
        if (Boolean.FALSE.equals(field.checkIfObstacle(destinationCoordinates))) {
            object.moovableTile.setObjectMovementProgress(0f);
            object.moovableTile.setTileRotation(updatedTankRotation);
            object.moovableTile.setTileDestinationCoordinates(destinationCoordinates);
        }
    }
}
