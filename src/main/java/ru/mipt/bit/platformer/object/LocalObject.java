package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;

public class LocalObject {
    private Tile tile;
    GridPoint2 coordinates;
    protected boolean obstacle;
    public LocalObject(Field field, Tile tile, boolean obstacle) {
        this.tile = tile;
        this.obstacle = obstacle;
        this.coordinates = tile.getTileCoordinates();
        field.setObject(this.tile.getTileCoordinates(), this);
    }

    public LocalObject() {
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
