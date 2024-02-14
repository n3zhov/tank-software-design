package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.observer.Publisher;
import ru.mipt.bit.platformer.observer.Subscriber;

import static com.badlogic.gdx.math.MathUtils.isEqual;

public class Bullet extends MoovableLocalObject implements Subscriber {
    MoovableTile bulletTile;

    Direction direction;

    public Bullet(Field field, MoovableTile moovableTile, Direction direction) {
        super(field, moovableTile, true);
        this.direction = direction;
    }

    public void render(float deltaTime, Field field) {
        this.moovableTile.render(deltaTime, field);
        field.setObject(this.moovableTile.getTileCoordinates(), this);
        this.Subscribe(field.getPublisher(this.moovableTile.tileCoordinates));

        if (isEqual(this.moovableTile.getObjectMovementProgress(), 1f)) {
            // record that the tank has reached his destination
            if (!this.moovableTile.tileCoordinates.equals(this.moovableTile.getTileDestinationCoordinates())) {
                this.Unsubscribe(field.getPublisher(this.moovableTile.tileCoordinates));
                field.deleteObstacle(this.moovableTile.tileCoordinates);

                this.moovableTile.setTileCoordinates(this.moovableTile.getTileDestinationCoordinates());
                field.setObject(this.moovableTile.getTileDestinationCoordinates(), this);
                this.Subscribe(field.getPublisher(this.moovableTile.tileCoordinates));
            }
        }
    }
    public void iterate(Field field) {
        if (isEqual(this.moovableTile.getObjectMovementProgress(), 1f)) {
            GridPoint2 destination = new GridPoint2(this.moovableTile.getTileCoordinates());
            switch (direction) {
                case RIGHT:
                    destination.x++;
                    moovableTile.setTileRotation(0f);
                    break;
                case LEFT:
                    destination.x--;
                    moovableTile.setTileRotation(-180f);
                    break;
                case UP:
                    destination.y++;
                    moovableTile.setTileRotation(90f);
                    break;
                case DOWN:
                    destination.y--;
                    moovableTile.setTileRotation(-90f);
                default:
                    break;
            }
            CollisionDetector.startMovement(this, destination, field, this.moovableTile.getTileRotation());
        }
    }

    public void delete(Field field) {
        field.deleteObstacle(this.moovableTile.getTileCoordinates());
        field.deleteObstacle(this.moovableTile.getTileDestinationCoordinates());
        this.getTile().dispose();
    }

    @Override
    public void takeHit(Field field) {
        delete(field);
    }

    @Override
    public void Subscribe(Publisher publisher) {
        publisher.setSubscriber(this);
    }

    @Override
    public void Unsubscribe(Publisher publisher) {
        publisher.setSubscriber(null);
    }
}
