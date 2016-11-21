package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by nguye on 21/11/2016.
 */

public class BunnyHead extends AbstractGameObject {
    public static final String TAG=BunnyHead.class.getName();
    private final float JUMP_TIME_MAX=0.3f;
    private final float JUMP_TIME_MIN=0.1f;
    private final float JUMP_TIME_OFFSET_FLYING=JUMP_TIME_MAX-0.018f;
    public enum VIEW_DIRECTION{LEFT,RIGHT}
    public enum JUMP_STATE{GROUNDED,FALLING,JUMP_RISING,JUMP_FALLING}
    private TextureRegion regHead;
    public  VIEW_DIRECTION viewDirection;
    public float timeJumping;
    public JUMP_STATE jumpState;
    public boolean hasFeatherPowerup;
    public float timeLeftFeatherPowerup;
    public BunnyHead(){
        init();
    }
    public void init(){
        dimension.set(1,1);
        regHead=Assets.instance.bunny.head;
        origin.set(dimension.x/2,dimension.y/2);
        bounds.set(0,0,dimension.x,dimension.y);
        terminalVelocity.set(3.0f,4.0f);
        friction.set(12f,0.0f);
        acceleration.set(0.0f,-25f);
        viewDirection=VIEW_DIRECTION.RIGHT;
        jumpState=JUMP_STATE.JUMP_FALLING;
        timeJumping=0;
        hasFeatherPowerup=false;
        timeLeftFeatherPowerup=0;
    }
    public void setTimeJumping(boolean jumpKeyPressed){};
    public void setFeatherPowerup(boolean pockedUp){};
    public boolean hasFeatherPowerup(){return false;}
    @Override
    public void render(SpriteBatch batch) {

    }
}
