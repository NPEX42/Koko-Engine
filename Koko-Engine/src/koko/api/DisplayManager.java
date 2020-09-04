package koko.api;

import koko.display.Window;
import koko.display.WindowFactory;

public class DisplayManager extends DisplayAPI {
	
	public static final DisplayManager INSTANCE = new DisplayManager();
	private DisplayManager() {}
	
	private Window window;
	@Override
	public void CreateDisplay(int width, int height, String title, boolean vsync) {
		window = WindowFactory.Create(width, height, title, vsync);
	}
	@Override
	public boolean UpdateDisplay() {
		return window.UpdateDisplay();
	}
	@Override
	public void DestroyDisplay() {
		window.CloseDisplay();
	}
	@Override
	protected void CreateDisplay_GLFW(int width, int height, String title, boolean vsync) {
		window = WindowFactory.RequestGLFW(width, height, title, vsync);
	}
	@Override
	protected void CreateDisplay_SFML(int width, int height, String title, boolean vsync) {
		window = WindowFactory.RequestSFML(width, height, title, vsync);
	}

}
