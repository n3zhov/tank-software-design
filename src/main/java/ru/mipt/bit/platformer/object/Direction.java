package ru.mipt.bit.platformer.object;

import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT;
    private static final Random RNG = new Random();

    public static Direction randomDirection()  {
        Direction[] objects = values();
        return objects[RNG.nextInt(objects.length)];
    }
}
