package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.observer.Publisher;
import ru.mipt.bit.platformer.observer.Subscriber;

import static com.badlogic.gdx.math.MathUtils.isEqual;
import static ru.mipt.bit.platformer.util.GdxGameUtils.continueProgress;

public class Tank extends MoovableLocalObject implements MoovableObjectInterface, Subscriber, TankInterface {
    public int getHealth() {
        return health;
    }
    private TankInterface state;

    private int health = 10;

    private float reloadProgress = 1f;
    private float reloadSpeed = 0.4f;
    public Tank(Field field, MoovableTile moovableTile) {
        super(field, moovableTile, true);
        field.getTanks().add(this);
        this.Subscribe(field.getPublisher(this.moovableTile.tileCoordinates));
        state = new LightDamaged(this);
    }

    public void render(float deltaTime, Field field) {
        state.render(deltaTime, field);
    }

    public void execute(Command command, Field field, TiledMapTileLayer groundLayer) {
        state.execute(command, field, groundLayer);
    }

    public void shoot(Field field, TiledMapTileLayer groundLayer) {
        state.shoot(field, groundLayer);
    }

    public void moveTo(Direction direction, Field field) {
        state.moveTo(direction, field);
    }

    public void drawInBatch(Batch batch){
        this.getTile().drawInBatch(batch);
    }

    @Override
    public void takeHit(Field field) {
        --health;
        if (health <= 7) {
            state = new MeduimDamaged(this);
            return;
        }
        if (health < 2) {
            state = new HeavyDamaged(this);
            return;
        }
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

    public void setHealth(int health) {
        this.health = health;
    }

    public float getReloadProgress() {
        return reloadProgress;
    }

    public void setReloadProgress(float reloadProgress) {
        this.reloadProgress = reloadProgress;
    }

    public float getReloadSpeed() {
        return reloadSpeed;
    }

    public void setReloadSpeed(float reloadSpeed) {
        this.reloadSpeed = reloadSpeed;
    }
}
