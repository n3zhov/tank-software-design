package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;

public class Field {
    private Tile[][] data;

    public Field(Tile[][] data) {
        this.data = data;
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                data[i][j] = new Tile();
            }
        }
    }
    public void setTile(GridPoint2 coordinates, Tile tile) {
        if (coordinates.x < data.length && coordinates.y < data[coordinates.x].length) {
            this.data[coordinates.x][coordinates.y] = tile;
        }
    }
    public boolean checkIfObstacle(GridPoint2 coordinates) {
        if (coordinates.x >=0 && coordinates.x < data.length && coordinates.y >= 0 && coordinates.y < data[coordinates.x].length) {
            return this.data[coordinates.x][coordinates.y].isObstacle();
        } else {
            return true;
        }
    }
}
