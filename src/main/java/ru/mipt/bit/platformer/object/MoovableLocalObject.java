package ru.mipt.bit.platformer.object;

import ru.mipt.bit.platformer.level.Field;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class MoovableLocalObject extends LocalObject {
    protected MoovableTile moovableTile;

    public MoovableLocalObject(Field field, MoovableTile moovableTile, boolean obstacle) {
        super(field, moovableTile, obstacle);
        this.moovableTile = moovableTile;
    }

    public MoovableTile getMoovableTile() {
        return moovableTile;
    }

    public void setMoovableTile(MoovableTile moovableTile) {
        this.moovableTile = moovableTile;
    }
}
