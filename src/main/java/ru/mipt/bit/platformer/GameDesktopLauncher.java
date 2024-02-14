package ru.mipt.bit.platformer;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.GridPoint2;
import ru.mipt.bit.platformer.level.TankGenerator;
import ru.mipt.bit.platformer.object.EnemyTank;
import ru.mipt.bit.platformer.object.MoovableObject;
import ru.mipt.bit.platformer.object.MoovableTile;
import ru.mipt.bit.platformer.player.Player;
import ru.mipt.bit.platformer.level.Field;
import ru.mipt.bit.platformer.level.FieldRenderer;
import ru.mipt.bit.platformer.level.TextLevelGenerator;
import ru.mipt.bit.platformer.object.Object;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static ru.mipt.bit.platformer.util.GdxGameUtils.*;

public class GameDesktopLauncher implements ApplicationListener {

    private Batch batch;
    private Field field;
    private FieldRenderer fieldRenderer;

    private Player player;

    private TiledMap level;
    private MapRenderer levelRenderer;

    private TankGenerator tankGenerator;

    @Override
    public void create() {
        batch = new SpriteBatch();
        field = new Field(new Object[10][8]);
        fieldRenderer = new FieldRenderer(field);

        // load level tiles
        level = new TmxMapLoader().load("level.tmx");
        levelRenderer = createSingleLayerMapRenderer(level, batch);
        TiledMapTileLayer groundLayer = getSingleLayer(level);

        //RandomLevelGenerator randomLevelGenerator = new RandomLevelGenerator(groundLayer, field);
        //file should have field size(10, 8)
        TextLevelGenerator textLevelGenerator = new TextLevelGenerator(groundLayer, field, "C:\\Users\\nikit\\IdeaProjects\\tank-software\\src\\main\\resources\\level.txt");
        textLevelGenerator.generate();

        tankGenerator = new TankGenerator(groundLayer, field, 2);
        tankGenerator.generate();

        player = field.getPlayer();
    }

    @Override
    public void render() {
        // clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT);

        // get time passed since the last render
        float deltaTime = Gdx.graphics.getDeltaTime();

        field.iterateTanks();

        field.render(deltaTime);

        // render each tile of the level
        levelRenderer.render();

        // start recording all drawing commands
        batch.begin();

        // render
        fieldRenderer.draw(batch);


        // submit all drawing requests
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // do not react to window resizing
    }

    @Override
    public void pause() {
        // game doesn't get paused
    }

    @Override
    public void resume() {
        // game doesn't get paused
    }

    @Override
    public void dispose() {
        // dispose of all the native resources (classes which implement com.badlogic.gdx.utils.Disposable)
        fieldRenderer.dispose();
        level.dispose();
        batch.dispose();
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        // level width: 10 tiles x 128px, height: 8 tiles x 128px
        config.setWindowedMode(1280, 1024);
        new Lwjgl3Application(new GameDesktopLauncher(), config);
    }
}
