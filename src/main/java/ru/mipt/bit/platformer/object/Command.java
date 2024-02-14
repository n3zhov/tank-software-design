package ru.mipt.bit.platformer.object;
import org.awesome.ai.Action;
public class Command {
    private TypeOfAction typeOfAction;
    private Direction direction;

    public Command(Action action) {
        switch(action) {
            case Shoot:
                this.typeOfAction = TypeOfAction.ShootAction;
                break;
            case MoveNorth:
                this.direction = Direction.UP;
                break;
            case MoveEast:
                this.direction = Direction.RIGHT;
                break;
            case MoveWest:
                this.direction = Direction.LEFT;
                break;
            case MoveSouth:
                this.direction = Direction.DOWN;
                break;
        }
    }
    public Command(TypeOfAction typeOfAction, Direction direction) {
        this.typeOfAction = typeOfAction;
        this.direction = direction;
    }

    public Command() {
    }

    public TypeOfAction getAction() {
        return typeOfAction;
    }

    public void setAction(TypeOfAction typeOfAction) {
        this.typeOfAction = typeOfAction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
