package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.object.EnemyTank;
import ru.mipt.bit.platformer.player.LocalPlayer;
import ru.mipt.bit.platformer.player.Tank;


import java.util.ArrayList;
import java.util.List;

public class Field {
    private MoovableTile playerTankTile;

    public List<Tank> getTanks() {
        return tanks;
    }

    private List<Tank> tanks;

    private LocalPlayer localPlayer;

    private Tank playerTank;
    public LocalObject[][] getData() {
        return data;
    }

    protected LocalObject[][] data;

    public Field(LocalObject[][] data) {
        this.data = data;
        this.tanks = new ArrayList<Tank>();
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                data[i][j] = new LocalObject();
            }
        }
    }

    public void render(float deltaTime) {
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(Boolean.TRUE.equals(this.checkIfObstacle(new GridPoint2(i, j))) && data[i][j] instanceof Tank) {
                    ((Tank) data[i][j]).render(deltaTime, this);
                }
            }
        }
    }

    public void setObject(GridPoint2 coordinates, LocalObject localObject) {
        if (coordinates.x < data.length && coordinates.y < data[coordinates.x].length) {
            this.data[coordinates.x][coordinates.y] = localObject;
        }
    }

    public LocalObject getObject(GridPoint2 coordinates) {
        if (coordinates.x < data.length && coordinates.y < data[coordinates.x].length) {
            return this.data[coordinates.x][coordinates.y];
        } else {
            return new LocalObject();
        }
    }
    public void deleteObstacle(GridPoint2 coordinates) {
        if (coordinates.x < data.length && coordinates.y < data[coordinates.x].length) {
            this.data[coordinates.x][coordinates.y] = new LocalObject();
        }
    }
    public boolean checkIfObstacle(GridPoint2 coordinates) {
        if (coordinates.x >=0 && coordinates.x < data.length && coordinates.y >= 0 && coordinates.y < data[coordinates.x].length) {
            return this.data[coordinates.x][coordinates.y].isObstacle();
        } else {
            return true;
        }
    }

    public void iterateTanks() {
        for(Tank tank: tanks) {
            if (tank instanceof EnemyTank) {
                ((EnemyTank) tank).iterate(this);
            } else {
                localPlayer.scanForKeys(this);
            }
        }
    }

    public MoovableTile getPlayerTankTile() {
        return playerTankTile;
    }

    public void setPlayerTankTile(MoovableTile playerTankTile) {
        this.playerTankTile = playerTankTile;
    }

    public LocalPlayer getPlayer() {
        return localPlayer;
    }

    public void setPlayer(LocalPlayer localPlayer) {
        this.localPlayer = localPlayer;
    }

    public Tank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(Tank playerTank) {
        this.playerTank = playerTank;
    }
}
