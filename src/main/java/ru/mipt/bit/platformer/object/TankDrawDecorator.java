package ru.mipt.bit.platformer.object;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import ru.mipt.bit.platformer.object.Tank;

import static ru.mipt.bit.platformer.util.GdxGameUtils.drawTextureRegionUnscaled;

public class TankDrawDecorator {
    Tank tank;
    boolean enableHealthBar = false;

    public TankDrawDecorator(Tank tank, boolean enableHealthBar) {
        this.tank = tank;
        this.enableHealthBar = enableHealthBar;
    }


    public void drawInBatch(Batch batch) {
        if (enableHealthBar) {
            drawTextureRegionUnscaled(batch, getHealthbarTexture(((float)tank.getHealth())/10f), createRectangle(), 0f);
        }
        tank.drawInBatch(batch);
    }

    private TextureRegion getHealthbarTexture(float relativeHealth) {
        var pixmap = new Pixmap(70, 3, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillRectangle(0, 0, 70, 3);
        pixmap.setColor(Color.GREEN);
        pixmap.fillRectangle(0, 0, (int) (70 * relativeHealth), 3);
        var texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegion(texture);
    }

    private Rectangle createRectangle() {
        Rectangle rectangle = new Rectangle(tank.getTile().getTileRectangle());
        rectangle.y += 90;
        rectangle.x += 12;
        return rectangle;
    }
}
