package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public interface TankInterface {
    void render(float deltaTime, Field field);

    void execute(Command command, Field field, TiledMapTileLayer groundLayer);

    void shoot(Field field, TiledMapTileLayer groundLayer);

    void moveTo(Direction direction, Field field);
}
