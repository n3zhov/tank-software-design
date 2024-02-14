package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.player.LocalPlayer;
import ru.mipt.bit.platformer.player.Tank;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.object.Tile;

import java.util.Random;

public class RandomLevelGenerator implements LevelGenerator {
    private Field field;
    TiledMapTileLayer groundLayer;
    private static final Random RNG = new Random();
    private static final String treePath = "images/greenTree.png";
    private static final String tankPath = "images/tank_blue.png";

    public RandomLevelGenerator(TiledMapTileLayer groundLayer, Field field) {
        this.groundLayer = groundLayer;
        this.field = field;
    }

    //field should have size (10, 8)
    public void generate() {
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 8; ++j) {
                Tile tile;
                LocalObject localObject;

                MapObject type = MapObject.randomMapObject();
                GridPoint2 coordinates = new GridPoint2(i, j);
                switch (type) {
                    case TREE:
                        tile = new Tile(groundLayer, treePath, coordinates);
                        localObject = new LocalObject(field, tile, true);
                        break;
                }
            }
        }

        GridPoint2 playerCoordinates = new GridPoint2(RNG.nextInt(10), RNG.nextInt(8));
        while (Boolean.TRUE.equals(field.checkIfObstacle(playerCoordinates))) {
            playerCoordinates = new GridPoint2(RNG.nextInt(10), RNG.nextInt(8));
        }

        field.setPlayerTankTile(new MoovableTile(groundLayer, tankPath,  playerCoordinates));

        field.setPlayerTank(new Tank(field, field.getPlayerTankTile()));

        field.setPlayer(new LocalPlayer(field.getPlayerTank()));
    }

    public Field getField() {
        return field;
    }
}
