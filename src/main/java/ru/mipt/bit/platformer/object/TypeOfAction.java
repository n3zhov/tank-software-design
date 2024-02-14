package ru.mipt.bit.platformer.object;

import java.util.Random;

public enum TypeOfAction {
    ShootAction,
    MoveAction,

    ToggleHealthBar;

    private static final Random RNG = new Random();

    public static TypeOfAction randomAction()  {
        TypeOfAction[] objects = values();
        return objects[RNG.nextInt(objects.length-1)];
    }
    
}
