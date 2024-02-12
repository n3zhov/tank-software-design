package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tile {
    protected Texture texture;
    protected TextureRegion tileRegion;
    protected GridPoint2 tileCoordinates;
    protected Rectangle tileRectangle;
    protected float tileRotation;
    protected boolean obstacle;
    public Tile(Field field, TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates, boolean obstacle) {
        this.texture = new Texture(pathToTexture);
        this.tileRegion = new TextureRegion(texture);
        this.tileCoordinates = coordinates;
        this.tileRectangle = new Rectangle();
        this.tileRectangle = createBoundingRectangle(tileRegion);
        this.obstacle = obstacle;

        moveRectangleAtTileCenter(tileLayer, tileRectangle, tileCoordinates);

        field.setTile(this.tileCoordinates, this);
    }

    public Tile() {
        obstacle = false;
    }

    public void dispose() {
        this.texture.dispose();
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void drawInBatch(Batch batch) {
        drawTextureRegionUnscaled(batch, tileRegion, tileRectangle, tileRotation);
    }
}
