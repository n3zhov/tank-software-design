package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class MoovableTile extends Tile {
    protected float movementSpeed = 0.4f;
    protected TileMovement tileMovement;
    protected GridPoint2 tileDestinationCoordinates;
    protected float objectMovementProgress = 1f;

    public MoovableTile(TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates) {
        super(tileLayer, pathToTexture, coordinates);
        tileMovement = new TileMovement(tileLayer, Interpolation.smooth);
        this.tileDestinationCoordinates = new GridPoint2(coordinates);
    }

    public MoovableTile(GridPoint2 coordinates) {
        super(coordinates);
        this.tileDestinationCoordinates = new GridPoint2(coordinates);
    }

    public void render(float deltaTime, Field field) {
        tileMovement.moveRectangleBetweenTileCenters(this.tileRectangle, this.tileCoordinates,
                this.tileDestinationCoordinates, this.objectMovementProgress);
        this.objectMovementProgress = continueProgress(this.objectMovementProgress, deltaTime, movementSpeed);
    }

    public GridPoint2 getTileDestinationCoordinates() {
        return tileDestinationCoordinates;
    }

    public TileMovement getTileMovement() {
        return tileMovement;
    }

    public float getObjectMovementProgress() {
        return objectMovementProgress;
    }

    public void setTileMovement(TileMovement tileMovement) {
        this.tileMovement = tileMovement;
    }

    public void setTileDestinationCoordinates(GridPoint2 tileDestinationCoordinates) {
        this.tileDestinationCoordinates = tileDestinationCoordinates;
    }

    public void setObjectMovementProgress(float objectMovementProgress) {
        this.objectMovementProgress = objectMovementProgress;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }
}
