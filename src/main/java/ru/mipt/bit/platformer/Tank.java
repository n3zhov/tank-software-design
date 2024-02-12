package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank extends MoovableObject implements MoovableObjectInterface{
    public Tank(Field field, TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates) {
        super(field, tileLayer, pathToTexture, coordinates, true);
    }

    public void render(float deltaTime, Field field) {
        tileMovement.moveRectangleBetweenTileCenters(this.tileRectangle, this.tileCoordinates, this.tileDestinationCoordinates, this.objectMovementProgress);
        this.objectMovementProgress = continueProgress(this.objectMovementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(this.objectMovementProgress, 1f)) {
            // record that the tank has reached his destination
            field.deleteObstacle(this.tileCoordinates);
            this.tileCoordinates.set(this.tileDestinationCoordinates);
            field.setTile(this.tileCoordinates, this);
        }
    }

    public void moveTo(Direction direction, Field field) {
        if (isEqual(objectMovementProgress, 1f)) {
            GridPoint2 destination = new GridPoint2(this.tileCoordinates);
            float updatedTankRotation = 0f;
            switch (direction) {
                case RIGHT:
                    destination.x++;
                    updatedTankRotation = 0f;
                    break;
                case LEFT:
                    destination.x--;
                    updatedTankRotation = -180f;
                    break;
                case UP:
                    destination.y++;
                    updatedTankRotation = 90f;
                    break;
                case DOWN:
                    destination.y--;
                    updatedTankRotation = -90f;
                default:
                    break;
            }
            CollisionDetector.startMovement(this, destination, field, updatedTankRotation);
        }
    }
}
