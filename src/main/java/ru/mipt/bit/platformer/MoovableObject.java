package ru.mipt.bit.platformer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.math.Interpolation;
import ru.mipt.bit.platformer.util.TileMovement;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoovableObject extends Object{
    protected MoovableTile moovableTile;

    public MoovableObject(Field field, MoovableTile moovableTile, boolean obstacle) {
        super(field, moovableTile, obstacle);
        this.moovableTile = moovableTile;
    }

    public MoovableTile getMoovableTile() {
        return moovableTile;
    }

    public void setMoovableTile(MoovableTile moovableTile) {
        this.moovableTile = moovableTile;
    }
}
