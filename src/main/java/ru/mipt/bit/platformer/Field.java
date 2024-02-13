package ru.mipt.bit.platformer;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Field {
    private MoovableTile playerTankTile;

    private Player player;

    private Tank playerTank;
    public Object[][] getData() {
        return data;
    }

    protected Object[][] data;

    public Field(Object[][] data) {
        this.data = data;
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                data[i][j] = new Object();
            }
        }
    }

    public void render(Batch batch) {
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(Boolean.TRUE.equals(this.checkIfObstacle(new GridPoint2(i, j)))) {
                    data[i][j].getTile().drawInBatch(batch);
                }
            }
        }
    }

    public void setObject(GridPoint2 coordinates, Object object) {
        if (coordinates.x < data.length && coordinates.y < data[coordinates.x].length) {
            this.data[coordinates.x][coordinates.y] = object;
        }
    }
    public void deleteObstacle(GridPoint2 coordinates) {
        if (coordinates.x < data.length && coordinates.y < data[coordinates.x].length) {
            this.data[coordinates.x][coordinates.y] = new Object();
        }
    }
    public boolean checkIfObstacle(GridPoint2 coordinates) {
        if (coordinates.x >=0 && coordinates.x < data.length && coordinates.y >= 0 && coordinates.y < data[coordinates.x].length) {
            return this.data[coordinates.x][coordinates.y].isObstacle();
        } else {
            return true;
        }
    }

    public MoovableTile getPlayerTankTile() {
        return playerTankTile;
    }

    public void setPlayerTankTile(MoovableTile playerTankTile) {
        this.playerTankTile = playerTankTile;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }
}
