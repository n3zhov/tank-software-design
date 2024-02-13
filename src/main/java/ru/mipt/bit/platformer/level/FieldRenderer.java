package ru.mipt.bit.platformer.level;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.object.Object;

public class FieldRenderer {
    private Field field;

    public FieldRenderer(Field field) {
        this.field = field;
    }

    public void draw(Batch batch) {
        Object[][] data = field.getData();
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(Boolean.TRUE.equals(field.checkIfObstacle(new GridPoint2(i, j)))) {
                    data[i][j].getTile().drawInBatch(batch);
                }
            }
        }
    }

    public void dispose () {
        Object[][] data = field.getData();
        for(int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(Boolean.TRUE.equals(field.checkIfObstacle(new GridPoint2(i, j)))) {
                    data[i][j].getTile().dispose();
                }
            }
        }
    }
}
