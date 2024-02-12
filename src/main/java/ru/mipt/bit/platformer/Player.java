package ru.mipt.bit.platformer;

import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.Input.Keys.*;
import static com.badlogic.gdx.Input.Keys.D;

public class Player {
    private Tank playerTank;

    public Player(Tank playerTank) {
        this.playerTank = playerTank;
    }

    public void scanForKeys(Field field) {
        if (Gdx.input.isKeyPressed(UP) || Gdx.input.isKeyPressed(W)) {
            playerTank.moveTo(Direction.UP, field);
        }
        if (Gdx.input.isKeyPressed(LEFT) || Gdx.input.isKeyPressed(A)) {
            playerTank.moveTo(Direction.LEFT, field);
        }
        if (Gdx.input.isKeyPressed(DOWN) || Gdx.input.isKeyPressed(S)) {
            playerTank.moveTo(Direction.DOWN, field);
        }
        if (Gdx.input.isKeyPressed(RIGHT) || Gdx.input.isKeyPressed(D)) {
            playerTank.moveTo(Direction.RIGHT, field);
        }
    }

    public void renderPlayerTank(float deltaTime, Field field) {
        playerTank.render(deltaTime, field);
    }
}
