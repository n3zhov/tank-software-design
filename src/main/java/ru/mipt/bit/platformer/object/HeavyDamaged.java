package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;

public class HeavyDamaged extends CommonTank implements TankInterface{
    public HeavyDamaged(Tank tank) {
        super(tank);
        this.tank.getMoovableTile().setMovementSpeed(0.4f*3f);
    }

    @Override
    public void shoot(Field field, TiledMapTileLayer groundLayer) {
        return;
    }
}
