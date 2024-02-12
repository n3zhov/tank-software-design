package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoovableObject extends Object{
    protected static final float MOVEMENT_SPEED = 0.4f;
    protected TileMovement tileMovement;
    protected GridPoint2 tileDestinationCoordinates;
    protected float objectMovementProgress = 1f;

    public MoovableObject(Field field, TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates, boolean obstacle) {
        super(field, tileLayer, pathToTexture, coordinates, obstacle);
        tileMovement = new TileMovement(tileLayer, Interpolation.smooth);
        this.tileDestinationCoordinates = new GridPoint2(coordinates);
    }
}
