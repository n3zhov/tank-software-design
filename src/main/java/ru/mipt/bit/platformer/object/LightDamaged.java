package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.level.Field;

public class LightDamaged extends CommonTank implements TankInterface{

    LightDamaged(Tank tank) {
        super(tank);
        this.tank.getMoovableTile().setMovementSpeed(0.4f);
        this.tank.setReloadSpeed(0.4f);
    }
}
