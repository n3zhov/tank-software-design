package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.level.Field;

public class MeduimDamaged extends CommonTank implements TankInterface{
    public MeduimDamaged(Tank tank) {
        super(tank);
        this.tank.moovableTile.setMovementSpeed(0.4f*2f);
        this.tank.setReloadSpeed(0.4f);
    }
}
