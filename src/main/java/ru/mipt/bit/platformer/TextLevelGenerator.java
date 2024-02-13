package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextLevelGenerator implements LevelGenerator{
    private Field field;
    TiledMapTileLayer groundLayer;
    private String filepath;

    private static final String treePath = "images/greenTree.png";
    private static final String tankPath = "images/tank_blue.png";

    public TextLevelGenerator(TiledMapTileLayer groundLayer, Field field, String path) {
        this.groundLayer = groundLayer;
        this.field = field;
        this.filepath = path;
    }

    public void generate() {
        try{
            BufferedReader br = new BufferedReader(new FileReader(this.filepath));
            String line;
            for (int i = 7; i >= 0; --i) {
                line = br.readLine();
                for (int j = 0; j < 10; ++j) {
                    GridPoint2 coordinates = new GridPoint2(j, i);
                    char ch = line.charAt(j);
                    if (ch == 'X') {
                        createPlayerTank(coordinates);
                    } else if (ch == 'T') {
                        createTree(coordinates);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createPlayerTank(GridPoint2 coordinates) {
        field.setPlayerTankTile(new MoovableTile(groundLayer, tankPath,  coordinates));

        field.setPlayerTank(new Tank(field, field.getPlayerTankTile()));

        field.setPlayer(new Player(field.getPlayerTank()));
    }

    private void createTree(GridPoint2 coordinates) {
        Tile tile;
        Object object;

        tile = new Tile(groundLayer, treePath, coordinates);
        object = new Object(field, tile, true);
    }
}
