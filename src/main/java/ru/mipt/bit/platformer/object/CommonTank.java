package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class CommonTank implements TankInterface {
    protected Tank tank;

    public CommonTank(Tank tank) {
        this.tank = tank;
    }

    public void render(float deltaTime, Field field) {
        this.tank.getMoovableTile().render(deltaTime, field);
        this.tank.setReloadProgress(continueProgress(this.tank.getReloadProgress(), deltaTime, this.tank.getReloadSpeed()));
        field.setObject(this.tank.getMoovableTile().getTileCoordinates(), this.tank);
        this.tank.Subscribe(field.getPublisher(this.tank.getMoovableTile().tileCoordinates));

        if (isEqual(this.tank.getMoovableTile().getObjectMovementProgress(), 1f)) {
            // record that the tank has reached his destination
            if (!this.tank.getMoovableTile().tileCoordinates.equals(this.tank.getMoovableTile().getTileDestinationCoordinates())) {
                this.tank.Unsubscribe(field.getPublisher(this.tank.getMoovableTile().tileCoordinates));
                field.deleteObstacle(this.tank.getMoovableTile().tileCoordinates);

                this.tank.getMoovableTile().setTileCoordinates(this.tank.getMoovableTile().getTileDestinationCoordinates());
                field.setObject(this.tank.getMoovableTile().getTileDestinationCoordinates(), this.tank);
                this.tank.Subscribe(field.getPublisher(this.tank.getMoovableTile().tileCoordinates));
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
            default:
                break;
        }
    }

    public void shoot(Field field, TiledMapTileLayer groundLayer) {
        if (tank.getReloadProgress() == 1f && this.tank.getMoovableTile().getObjectMovementProgress() == 1f) {
            GridPoint2 coordinates = new GridPoint2(this.tank.getMoovableTile().getTileCoordinates());
            Direction direction;
            if (this.tank.getMoovableTile().getTileRotation() == 90f) {
                coordinates.y++;
                direction = Direction.UP;
            } else if (this.tank.getMoovableTile().getTileRotation() == -90f) {
                coordinates.y--;
                direction = Direction.DOWN;
            } else if (this.tank.getMoovableTile().getTileRotation() == 0f) {
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
                bulletTile.setTileRotation(this.tank.getMoovableTile().getTileRotation());
                Bullet bullet = new Bullet(field, bulletTile, direction);
            }

            tank.setReloadProgress(0f);
        }

    }

    public void moveTo(Direction direction, Field field) {
        if (isEqual(this.tank.getMoovableTile().getObjectMovementProgress(), 1f)) {
            GridPoint2 destination = new GridPoint2(this.tank.getMoovableTile().getTileCoordinates());
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
            CollisionDetector.startMovement(this.tank, destination, field, updatedTankRotation);
        }
    }
}
