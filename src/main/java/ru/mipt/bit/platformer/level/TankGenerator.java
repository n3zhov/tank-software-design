package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.object.EnemyTank;
import ru.mipt.bit.platformer.object.MoovableTile;

import java.util.Random;

public class TankGenerator {
    private Field field;
    private final String tankPath = "images/tank_blue.png";
    Random RNG;
    TiledMapTileLayer groundLayer;

    private int tankCount;

    public TankGenerator(TiledMapTileLayer groundLayer, Field field, int tankCount) {
        this.field = field;
        this.tankCount = tankCount;
        this.groundLayer = groundLayer;
        this.RNG = new Random();
    }

    public void generate() {
        for (int i = 0; i < tankCount; ++i) {
            GridPoint2 tankCoordinates = new GridPoint2(RNG.nextInt(10), RNG.nextInt(8));
            while (Boolean.TRUE.equals(field.checkIfObstacle(tankCoordinates))) {
                tankCoordinates = new GridPoint2(RNG.nextInt(10), RNG.nextInt(8));
            }

            MoovableTile tankTile = new MoovableTile(groundLayer, tankPath, tankCoordinates);

            EnemyTank enemyTank = new EnemyTank(field, tankTile);
        }
    }

}
