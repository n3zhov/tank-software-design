package ru.mipt.bit.platformer.player;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.*;
import ru.mipt.bit.platformer.observer.Publisher;
import ru.mipt.bit.platformer.observer.Subscriber;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank extends MoovableLocalObject implements MoovableObjectInterface, Subscriber {
    private int health = 10;

    private float reloadProgress = 1f;
    private float RELOAD_SPEED = 0.4f;
    public Tank(Field field, MoovableTile moovableTile) {
        super(field, moovableTile, true);
        field.getTanks().add(this);
        this.Subscribe(field.getPublisher(this.moovableTile.tileCoordinates));
    }

    public void render(float deltaTime, Field field) {
        this.moovableTile.render(deltaTime, field);
        this.reloadProgress = continueProgress(this.reloadProgress, deltaTime, RELOAD_SPEED);
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

    public void execute(Command command, Field field, TiledMapTileLayer groundLayer) {
        switch (command.getAction()){
            case MoveAction:
                this.moveTo(command.getDirection(), field);
                break;
            case ShootAction:
                this.shoot(field, groundLayer);
                break;
        }
    }

    private void shoot(Field field, TiledMapTileLayer groundLayer) {
        if (reloadProgress == 1f && this.moovableTile.getObjectMovementProgress() == 1f) {
            GridPoint2 coordinates = new GridPoint2(this.moovableTile.getTileCoordinates());
            Direction direction;
            if (this.moovableTile.getTileRotation() == 90f) {
                coordinates.y++;
                direction = Direction.UP;
            } else if (this.moovableTile.getTileRotation() == -90f) {
                coordinates.y--;
                direction = Direction.DOWN;
            } else if (this.moovableTile.getTileRotation() == 0f) {
                coordinates.x++;
                direction = Direction.RIGHT;
            } else {
                coordinates.x--;
                direction = Direction.LEFT;
            }

            if ((coordinates.x < 10 && coordinates.y < 8 && coordinates.x >= 0 && coordinates.y >= 0) &&
                    Boolean.TRUE.equals(field.checkIfObstacle(coordinates))) {
                field.getPublisher(coordinates).notifySubscriber(field);
            } else if (coordinates.x < 10 && coordinates.y < 8 && coordinates.x > 0 && coordinates.y > 0) {
                MoovableTile bulletTile = new MoovableTile(groundLayer, "images/bullet.png", coordinates);
                bulletTile.setTileRotation(this.getMoovableTile().getTileRotation());
                Bullet bullet = new Bullet(field, bulletTile, direction);
            }

            reloadProgress = 0f;
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

    @Override
    public void takeHit(Field field) {
        --health;
        if (health == 0) {
               field.setObject(this.moovableTile.getTileCoordinates(), new LocalObject());
            field.setObject(this.moovableTile.getTileDestinationCoordinates(), new LocalObject());
            this.getTile().dispose();
        }
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
