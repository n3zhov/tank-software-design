package ru.mipt.bit.platformer.object;
import ru.mipt.bit.platformer.object.Direction;
import ru.mipt.bit.platformer.object.Action;

public class Command {
    private Action action;
    private Direction direction;
    public Command(Action action, Direction direction) {
        this.action = action;
        this.direction = direction;
    }

    public Command() {
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
