package ru.mipt.bit.platformer.object;

import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.Action;
import ru.mipt.bit.platformer.object.Command;
import ru.mipt.bit.platformer.object.Direction;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.player.Tank;

public class EnemyTank extends Tank {
    public EnemyTank(Field field, MoovableTile moovableTile) {
        super(field, moovableTile);
    }

    public void iterate(Field field) {
        this.execute(new Command(Action.randomAction(), Direction.randomDirection()), field);
    }
}
