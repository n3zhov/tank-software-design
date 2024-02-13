package ru.mipt.bit.platformer.player;

import com.badlogic.gdx.Gdx;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.Action;
import ru.mipt.bit.platformer.object.Command;
import ru.mipt.bit.platformer.object.Direction;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class Player {
    private Tank playerTank;

    public Player(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public void scanForKeys(Field field) {
        Command command = new Command();
        command.setAction(Action.MoveAction);
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
        if (command.getDirection() != null) {
            playerTank.execute(command, field);
        }
    }

    public void renderPlayerTank(float deltaTime, Field field) {
        playerTank.render(deltaTime, field);
    }
}
