package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Object extends Tile {
    protected boolean obstacle;
    public Object(Field field, TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates, boolean obstacle) {
        super(tileLayer, pathToTexture, coordinates);
        this.obstacle = obstacle;

        field.setTile(this.tileCoordinates, this);
    }

    public Object() {
        super();
        obstacle = false;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }
}
