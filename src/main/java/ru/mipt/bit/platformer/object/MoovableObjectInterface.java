package ru.mipt.bit.platformer.object;

import ru.mipt.bit.platformer.level.Field;

public interface MoovableObjectInterface {
    void moveTo(Direction direction, Field field);
    void render(float deltaTime, Field field);
}
