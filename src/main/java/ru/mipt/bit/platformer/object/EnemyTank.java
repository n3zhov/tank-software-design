package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.level.Field;

public class EnemyTank extends Tank {
    public EnemyTank(Field field, MoovableTile moovableTile) {
        super(field, moovableTile);
    }

    public void iterate(Field field, TiledMapTileLayer groundLayer) {
        this.execute(new Command(TypeOfAction.randomAction(), Direction.randomDirection()), field, groundLayer);
    }
}
