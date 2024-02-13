package ru.mipt.bit.platformer;

public interface MoovableObjectInterface {
    void moveTo(Direction direction, Field field);
    void render(float deltaTime, Field field);
}
