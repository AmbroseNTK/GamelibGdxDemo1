package kiet.nguyentuan.libgdx.demo1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sun.org.apache.xpath.internal.operations.String;

import net.dermetfan.gdx.math.MathUtils;

public class MainActivity extends ApplicationAdapter {

	private WorldController worldController;
	private WorldRenderer worldRenderer;

	private boolean paused;

	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		worldController=new WorldController();
		worldRenderer=new WorldRenderer(worldController);

		paused=false;
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		worldRenderer.resize(width,height);
	}

	@Override
	public void render () {
		if(!paused) {
			worldController.update(Gdx.graphics.getDeltaTime());
		}
		Gdx.gl.glClearColor(0x64/255f,0x95/255f,0xed/255f,0xff/255f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		worldRenderer.render();

	}

	@Override
	public void pause() {
		super.pause();
		paused=true;
	}

	@Override
	public void resume() {
		super.resume();
		paused=false;
	}

	@Override
	public void dispose () {
		worldRenderer.dispose();
	}
}
