package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Created by nguye on 03/11/2016.
 */
public class WorldController extends InputAdapter {
    public Level level;
    public int lives;
    public int score;
    private void initLevel(){
        score=0;
        level=new Level(Constants.LEVEL_01);
    }
    private static String TAG=WorldController.class.getName();
    public CameraHelper cameraHelper;

    public WorldController(){
        init();
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode== Input.Keys.R){
            init();
            Gdx.app.debug(TAG,"Game restart");
        }
        if(keycode== Input.Keys.SPACE){
            if(cameraHelper.hasTarget()){

            }

        }
        if(keycode== Input.Keys.ENTER){
            Gdx.app.debug(TAG,"Camera follow enabled: "+cameraHelper.hasTarget());
        }
        return super.keyUp(keycode);
    }

    private void init(){
        Gdx.input.setInputProcessor(this);
        cameraHelper=new CameraHelper();
        lives=Constants.LIVES_START;
        initLevel();
    }

    private Pixmap createProceduralPixmap(int width, int height) {
        Pixmap pixmap=new Pixmap(width,height, Pixmap.Format.RGBA8888);
        pixmap.setColor(1,0,0,0.5f);
        pixmap.fill();
        pixmap.setColor(1,1,0,1);
        pixmap.drawLine(0,0,width,height);
        pixmap.drawLine(width,0,0,height);
        pixmap.setColor(0,1,1,1);
        pixmap.drawRectangle(0,0,width,height);
        return pixmap;
    }


    public void update(float deltaTime){
        handleDebugInput(deltaTime);
        cameraHelper.update(deltaTime);
    }

    private void handleDebugInput(float deltaTime) {

        if(Gdx.app.getType() != Application.ApplicationType.Desktop)
            return;

        float camMoveSpeed=5*deltaTime;
        float cameMoveAccelFactor=5;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            camMoveSpeed*=cameMoveAccelFactor;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            moveCamera(-camMoveSpeed,0);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            moveCamera(camMoveSpeed,0);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            moveCamera(0,camMoveSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            moveCamera(0,-camMoveSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE))
            moveCamera(0,0);

        float camZoomSpeed =1*deltaTime;
        float camZoomAccelFactor=5;
        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            camZoomSpeed*=camZoomAccelFactor;
        if(Gdx.input.isKeyPressed(Input.Keys.COMMA))
            cameraHelper.addZoom(camZoomSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.PERIOD))
            cameraHelper.addZoom(-camZoomSpeed);
        if(Gdx.input.isKeyPressed(Input.Keys.SLASH))
            cameraHelper.addZoom(1);

    }

    private void moveCamera(float x,float y) {
        x+=cameraHelper.getPosition().x;
        y+=cameraHelper.getPosition().y;
        cameraHelper.setPosition((float)x,(float)y);
    }
}
