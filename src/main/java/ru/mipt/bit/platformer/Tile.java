package ru.mipt.bit.platformer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Rectangle;

import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class Tile {
    public Tile(TiledMapTileLayer tileLayer, String pathToTexture, GridPoint2 coordinates) {
        this.texture = new Texture(pathToTexture);
        this.tileRegion = new TextureRegion(texture);
        this.tileCoordinates = coordinates;
        this.tileRectangle = new Rectangle();
        this.tileRectangle = createBoundingRectangle(tileRegion);

        moveRectangleAtTileCenter(tileLayer, tileRectangle, tileCoordinates);
    }
    public Tile(GridPoint2 coordinates) {
        this.tileCoordinates = coordinates;
    }
    protected Texture texture;
    protected TextureRegion tileRegion;
    public GridPoint2 tileCoordinates;

    public void setTileRectangle(Rectangle tileRectangle) {
        this.tileRectangle = tileRectangle;
    }

    protected Rectangle tileRectangle;

    public float getTileRotation() {
        return tileRotation;
    }

    protected float tileRotation;

    public void dispose() {
        this.texture.dispose();
    }

    public void drawInBatch(Batch batch) {
        drawTextureRegionUnscaled(batch, tileRegion, tileRectangle, tileRotation);
    }

    public void setTileRotation(float tileRotation) {
        this.tileRotation = tileRotation;
    }

    public void setTileCoordinates(GridPoint2 tileCoordinates) {
        this.tileCoordinates = tileCoordinates;
    }

    public GridPoint2 getTileCoordinates() {
        return tileCoordinates;
    }

    public Rectangle getTileRectangle() {
        return tileRectangle;
    }
}
