package kiet.nguyentuan.libgdx.demo1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import kiet.nguyentuan.libgdx.demo1.MainActivity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=480;
		new LwjglApplication(new MainActivity(), config);
	}
}
