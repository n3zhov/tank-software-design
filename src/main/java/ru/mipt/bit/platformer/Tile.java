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
    public Tile() {};
    protected Texture texture;
    protected TextureRegion tileRegion;
    protected GridPoint2 tileCoordinates;
    protected Rectangle tileRectangle;
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
}
