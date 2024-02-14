package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.object.Tank;
import ru.mipt.bit.platformer.object.TankDrawDecorator;

public class FieldRenderer {
    private Field field;

    public FieldRenderer(Field field) {
        this.field = field;
    }

    public void draw(Batch batch) {
        LocalObject[][] data = field.getData();
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if (data[i][j] instanceof Tank) {
                    TankDrawDecorator drawDecorator = new TankDrawDecorator(((Tank)data[i][j]), field.isEnableHealthBar());
                    drawDecorator.drawInBatch(batch);
                } else if(Boolean.TRUE.equals(field.checkIfObstacle(new GridPoint2(i, j)))) {
                    data[i][j].getTile().drawInBatch(batch);
                }
            }
        }
    }

    public void dispose () {
        LocalObject[][] data = field.getData();
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(Boolean.TRUE.equals(field.checkIfObstacle(new GridPoint2(i, j)))) {
                    data[i][j].getTile().dispose();
                }
            }
        }
    }
}
