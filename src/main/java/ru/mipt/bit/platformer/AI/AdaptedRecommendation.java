package ru.mipt.bit.platformer.AI;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.GridPoint2;
import org.awesome.ai.Recommendation;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.Command;
import ru.mipt.bit.platformer.object.Tank;

public class AdaptedRecommendation {
    private Object object;
    private Command command;

    public AdaptedRecommendation(Recommendation recommendation, Field field) {
        this.command = new Command(recommendation.getAction());
        this.object = field.getObject(new GridPoint2(recommendation.getActor().getX(), recommendation.getActor().getY()));
    }

    public void act(Field field, TiledMapTileLayer groundLayer) {
        if (this.object != null && this.object instanceof Tank) {
            ((Tank) this.object).execute(command, field, groundLayer);
        }
    }
}
