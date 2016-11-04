package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.sun.media.jfxmediaimpl.MediaDisposer;

/**
 * Created by nguye on 03/11/2016.
 */
public class WorldRenderer implements Disposable {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private WorldController worldController;
    public WorldRenderer(WorldController worldController){
        this.worldController=worldController;
        init();
    }
    private void init(){
        batch=new SpriteBatch();
        camera=new OrthographicCamera((float)Constants.VIEWPORT_WIDTH,(float)Constants.VIEWPORT_HEIGHT);
        camera.position.set(0,0,0);
        camera.update();
    }
    public void resize(int width,int height){
        camera.viewportWidth=((float)Constants.VIEWPORT_HEIGHT/height)*width;
        camera.update();
    }
    public void render(){
        renderTestObjects();
    }

    private void renderTestObjects() {
        worldController.cameraHelper.applyTo(camera);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for(Sprite sprite:worldController.testSprites){
            sprite.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
