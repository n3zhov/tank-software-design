package ru.mipt.bit.platformer.object;

import java.util.Random;

public enum Action {
    ShootAction,
    MoveAction;

    private static final Random RNG = new Random();

    public static Action randomAction()  {
        Action[] objects = values();
        return objects[RNG.nextInt(objects.length)];
    }
    
}
