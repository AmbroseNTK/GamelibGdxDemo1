package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by nguye on 20/11/2016.
 */

public class Level {

    public Array<Rock> rocks;
    public Clouds clouds;
    public Mountain mountain;
    public WaterOverlay waterOverlay;

    public static final String TAG = Level.class.getName();

    public enum BLOCK_TYPE {
        EMPTY(0, 0, 0),
        ROCK(0, 255, 0),
        PLAYER_SPAWNPOINT(255, 255, 255),
        ITEM_FEATHER(255, 0, 255),
        ITEM_GOLD_COIN(255, 255, 0);
        private int color;

        private BLOCK_TYPE(int r, int g, int b) {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }

        public boolean sameColor(int color) {
            return this.color == color;
        }

        public int getColor() {
            return color;
        }
    }

    public Level(String fileName) {
        init(fileName);
    }

    private void init(String fileName) {
        rocks = new Array<Rock>();
        Pixmap pixmap = new Pixmap(Gdx.files.internal(fileName));
        int lastPixel = -1;
        for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
            for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
                AbstractGameObject obj = null;
                float offsetHeight = 0;
                float baseHeight = pixmap.getHeight() - pixelY;
                int currentPixel = pixmap.getPixel(pixelX, pixelY);
                if (BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
                } else if (BLOCK_TYPE.ROCK.sameColor(currentPixel)) {
                    if (lastPixel != currentPixel) {
                        obj = new Rock();
                        float heightIncFact = 0.25f;
                        offsetHeight = -2.5f;
                        obj.position.set(pixelX, baseHeight * obj.dimension.y * heightIncFact + offsetHeight);
                        rocks.add((Rock) obj);
                    } else {
                        rocks.get(rocks.size - 1).increaseLength(1);
                    }
                } else if (BLOCK_TYPE.PLAYER_SPAWNPOINT.sameColor(currentPixel)) {
                } else if (BLOCK_TYPE.ITEM_FEATHER.sameColor(currentPixel)) {
                } else if (BLOCK_TYPE.ITEM_GOLD_COIN.sameColor(currentPixel)) {
                } else {
                    int r = 0xff & (currentPixel >>> 24);
                    int g = 0xff & (currentPixel >>> 16);
                    int b = 0xff & (currentPixel >> 8);
                    int a = 0xff & currentPixel;
                    Gdx.app.error(TAG, "Unknown object at x<" + pixelX + "> y<" + pixelY + ">: r<" + r + "> g<" + g + "> b<" + b + "> a<" + a + ">");
                }
                lastPixel = currentPixel;
            }
        }
        clouds =new Clouds(pixmap.getWidth());
        clouds.position.set(0,2);
        mountain=new Mountain(pixmap.getWidth());
        mountain.position.set(-1,-1);
        waterOverlay = new WaterOverlay(pixmap.getWidth());
        waterOverlay.position.set(0,-3.75f);
        pixmap.dispose();
        Gdx.app.debug(TAG,"level '"+fileName+"' loaded");
    }

    public void render(SpriteBatch batch){
        mountain.render(batch);
        for(Rock rock:rocks)
            rock.render(batch);
        waterOverlay.render(batch);
        clouds.render(batch);
    }
}
