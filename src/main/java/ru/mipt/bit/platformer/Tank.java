package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank extends Tile{
    private static final float MOVEMENT_SPEED = 0.4f;
    private TileMovement tileMovement;
    private GridPoint2 tankDestinationCoordinates;
    private float tankMovementProgress = 1f;
    public Tank(Field field, TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates) {
        super(field, tileLayer, pathToTexture, coordinates, true);
        this.tankDestinationCoordinates = new GridPoint2(coordinates);
        tileMovement = new TileMovement(tileLayer, Interpolation.smooth);
    }

    public void render(float deltaTime, Field field) {
        tileMovement.moveRectangleBetweenTileCenters(this.tileRectangle, this.tileCoordinates, this.tankDestinationCoordinates, this.tankMovementProgress);
        this.tankMovementProgress = continueProgress(this.tankMovementProgress, deltaTime, MOVEMENT_SPEED);
        if (isEqual(this.tankMovementProgress, 1f)) {
            // record that the tank has reached his destination
            field.deleteObstacle(this.tileCoordinates);
            this.tileCoordinates.set(this.tankDestinationCoordinates);
            field.setTile(this.tileCoordinates, this);
        }
    }

    public void moveTo(Direction direction, Field field) {
        if (isEqual(tankMovementProgress, 1f)) {
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
            if (Boolean.FALSE.equals(field.checkIfObstacle(destination))) {
                this.tankMovementProgress = 0f;
                this.tileRotation = updatedTankRotation;
                this.tankDestinationCoordinates.set(destination);
            }
        }
    }
}
