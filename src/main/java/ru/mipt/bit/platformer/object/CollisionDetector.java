package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;

public class CollisionDetector{
    static public void startMovement(MoovableLocalObject object, GridPoint2 destinationCoordinates, Field field, float updatedTankRotation) {

        if (object instanceof Bullet && (destinationCoordinates.x < 0 ||destinationCoordinates.x >= 10 || destinationCoordinates.y >= 8 || destinationCoordinates.y < 0)) {
            ((Bullet) object).delete(field);
            field.setObject(object.getMoovableTile().getTileCoordinates(), new LocalObject());
        } else if (Boolean.FALSE.equals(field.checkIfObstacle(destinationCoordinates))) {
            object.moovableTile.setObjectMovementProgress(0f);
            object.moovableTile.setTileRotation(updatedTankRotation);
            object.moovableTile.setTileDestinationCoordinates(destinationCoordinates);
            field.setObject(destinationCoordinates, object);
        } else if (object instanceof Bullet && Boolean.TRUE.equals(field.checkIfObstacle(destinationCoordinates))) {
            field.getPublisher(destinationCoordinates).notifySubscriber(field);
            ((Bullet) object).delete(field);
        }
    }
}
