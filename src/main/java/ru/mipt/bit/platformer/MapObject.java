package ru.mipt.bit.platformer;

import java.util.Random;

public enum MapObject {
    TREE,
    GRASS;
    private static final Random RNG = new Random();

    public static MapObject randomMapObject()  {
        MapObject[] objects = values();
        return objects[RNG.nextInt(objects.length)];
    }
}
