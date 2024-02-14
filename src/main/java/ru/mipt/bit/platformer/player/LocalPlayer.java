package ru.mipt.bit.platformer.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.TypeOfAction;
import ru.mipt.bit.platformer.object.Command;
import ru.mipt.bit.platformer.object.Direction;

import javax.swing.*;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class LocalPlayer {
    public Tank getPlayerTank() {
        return playerTank;
    }

    private Tank playerTank;

    public LocalPlayer(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public void scanForKeys(Field field, TiledMapTileLayer groundLayer) {
        Command command = new Command();
        command.setAction(TypeOfAction.MoveAction);
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            command.setDirection(Direction.UP);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            command.setDirection(Direction.LEFT);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            command.setDirection(Direction.DOWN);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            command.setDirection(Direction.RIGHT);
        }
        if (Gdx.input.isKeyPressed(SPACE)) {
            command.setAction(TypeOfAction.ShootAction);
        }
          if (command.getDirection() != null || command.getAction() == TypeOfAction.ShootAction) {
            playerTank.execute(command, field, groundLayer);
        }
    }

    public void renderPlayerTank(float deltaTime, Field field) {
        playerTank.render(deltaTime, field);
    }
}
