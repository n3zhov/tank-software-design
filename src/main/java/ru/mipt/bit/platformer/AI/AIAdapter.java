package ru.mipt.bit.platformer.AI;

import com.badlogic.gdx.math.GridPoint2;
import org.awesome.ai.AI;
import org.awesome.ai.Action;
import org.awesome.ai.Recommendation;
import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import org.awesome.ai.strategy.NotRecommendingAI;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.object.EnemyTank;
import ru.mipt.bit.platformer.object.LocalObject;
import ru.mipt.bit.platformer.player.LocalPlayer;
import ru.mipt.bit.platformer.player.Tank;

import java.util.ArrayList;
import java.util.List;

public class AIAdapter {
    private Field field;
    private Player player;
    private GameState gameState;
    private NotRecommendingAI ai;

    public AIAdapter(Field field) {
        this.field = field;
        GameState.GameStateBuilder gameStateBuilder = gameState.builder();

        buildPlayer();
        gameStateBuilder.player(player);


        gameStateBuilder.obstacles(buildTrees(field));
        gameStateBuilder.bots(buildBots(field));
        gameStateBuilder.levelWidth(10);
        gameStateBuilder.levelHeight(8);

        gameState = gameStateBuilder.build();

        ai = new NotRecommendingAI();
    }

    private void buildPlayer() {
        Player.PlayerBuilder playerBuilder = Player.builder();
        LocalPlayer localPlayer = field.getPlayer();
        playerBuilder.source(localPlayer);
        playerBuilder.x(localPlayer.getPlayerTank().getMoovableTile().getTileCoordinates().x);
        playerBuilder.y(localPlayer.getPlayerTank().getMoovableTile().getTileCoordinates().y);
        playerBuilder.destX(localPlayer.getPlayerTank().getMoovableTile().getTileDestinationCoordinates().x);
        playerBuilder.destY(localPlayer.getPlayerTank().getMoovableTile().getTileDestinationCoordinates().y);

        float localPlayerRotation = localPlayer.getPlayerTank().getMoovableTile().getTileRotation();
        if (localPlayerRotation == 0f) {
            playerBuilder.orientation(Orientation.E);
        } else if (localPlayerRotation == -180f) {
            playerBuilder.orientation(Orientation.W);
        } else if (localPlayerRotation == 90f) {
            playerBuilder.orientation(Orientation.N);
        } else if (localPlayerRotation == -90f) {
            playerBuilder.orientation(Orientation.S);
        }

        player = playerBuilder.build();
    }

    private Bot buildBot(EnemyTank tank) {
        Bot.BotBuilder botBuilder = Bot.builder();
        botBuilder.source(tank);
        botBuilder.x(tank.getMoovableTile().getTileCoordinates().x);
        botBuilder.y(tank.getMoovableTile().getTileCoordinates().y);
        botBuilder.destX(tank.getMoovableTile().getTileDestinationCoordinates().x);
        botBuilder.destY(tank.getMoovableTile().getTileDestinationCoordinates().y);


        float tankOrientation = tank.getMoovableTile().getTileRotation();
        if (tankOrientation == 0f) {
            botBuilder.orientation(Orientation.E);
        } else if (tankOrientation == -180f) {
            botBuilder.orientation(Orientation.W);
        } else if (tankOrientation == 90f) {
            botBuilder.orientation(Orientation.N);
        } else if (tankOrientation == -90f) {
            botBuilder.orientation(Orientation.S);
        }

        return botBuilder.build();
    }

    private ArrayList<Obstacle> buildTrees(Field field) {
        ArrayList<Obstacle> result = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 8; ++j){
                LocalObject object = field.getObject(new GridPoint2(i, j));
                if (object.isObstacle() && !(object instanceof Tank)) {
                    result.add(new Obstacle(i, j));
                }
            }
        }
        return result;
    }

    private ArrayList<Bot> buildBots(Field field) {
        ArrayList<Bot> result = new ArrayList<>();
        for (int i = 0; i < 10; ++i){
            for (int j = 0; j < 8; ++j){
                LocalObject object = field.getObject(new GridPoint2(i, j));
                EnemyTank tank;
                if (object instanceof EnemyTank) {
                    tank = (EnemyTank) object;
                    GridPoint2 coordinates = new GridPoint2(i, j);
                    if (coordinates.equals(tank.getMoovableTile().getTileCoordinates())) {
                        result.add(buildBot(tank));
                    }
                }
            }
        }
        return result;
    }

    public void stepAI() {
        ArrayList<AdaptedRecommendation> adaptedRecommendations = getRecommendations();
        for (AdaptedRecommendation recommendation : adaptedRecommendations) {
            recommendation.act(field);
        }
    }
    private ArrayList<AdaptedRecommendation> getRecommendations() {
        ArrayList<AdaptedRecommendation> adaptedRecommendations = new ArrayList<>();
        List<Recommendation> recommendations = ai.recommend(gameState);
        for (Recommendation recommendation : recommendations) {
            adaptedRecommendations.add(new AdaptedRecommendation(recommendation, field));
        }
        return adaptedRecommendations;
    }
}
