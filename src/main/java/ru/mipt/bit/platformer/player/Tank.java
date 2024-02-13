package ru.mipt.bit.platformer.player;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.*;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Tank extends MoovableObject implements MoovableObjectInterface {
    public Tank(Field field, MoovableTile moovableTile) {
        super(field, moovableTile, true);
    }

    public void render(float deltaTime, Field field) {
        this.moovableTile.render(deltaTime, field);
        field.setObject(this.moovableTile.getTileCoordinates(), this);

        if (isEqual(this.moovableTile.getObjectMovementProgress(), 1f)) {
            // record that the tank has reached his destination
            field.deleteObstacle(this.moovableTile.tileCoordinates);
            this.moovableTile.setTileCoordinates(this.moovableTile.getTileDestinationCoordinates());
            field.setObject(this.moovableTile.getTileCoordinates(), this);
        }
    }

    public void moveTo(Direction direction, Field field) {
        if (isEqual(this.moovableTile.getObjectMovementProgress(), 1f)) {
            GridPoint2 destination = new GridPoint2(this.moovableTile.getTileCoordinates());
            float updatedTankRotation = 0f;
            switch (direction) {
                case RIGHT:
                    destination.x++;
                    updatedTankRotation = 0f;
                    break;
                case LEFT:
                    destination.x--;
                    updatedTankRotation = -180f;
                    break;
                case UP:
                    destination.y++;
                    updatedTankRotation = 90f;
                    break;
                case DOWN:
                    destination.y--;
                    updatedTankRotation = -90f;
                default:
                    break;
            }
            CollisionDetector.startMovement(this, destination, field, updatedTankRotation);
        }
    }
}
