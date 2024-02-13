package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

public class Object {
    private Tile tile;
    GridPoint2 coordinates;
    protected boolean obstacle;
    public Object(Field field, Tile tile, boolean obstacle) {
        this.tile = tile;
        this.obstacle = obstacle;
        this.coordinates = tile.getTileCoordinates();

        field.setObject(this.tile.getTileCoordinates(), this);
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

    public Tile getTile() {
        return tile;
    }
}
