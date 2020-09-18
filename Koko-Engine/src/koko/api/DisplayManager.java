package koko.api;

import koko.display.Window;
import koko.display.WindowFactory;
import koko.events.EventSystem;
/**
 * @author Npex42
 * @version 0.5.0-alpha
 */
public class DisplayManager extends DisplayAPI {
	
	private int width, height;
	
	public static final DisplayManager INSTANCE = new DisplayManager();
	private DisplayManager() {
		EventSystem.RegisterResizeHandler(DisplayManager::HandleResize);
	}
	
	private Window window;
	@Override
	public void CreateDisplay(int width, int height, String title, boolean vsync) {
		this.width = width;
		this.height = height;
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
	
	public static void HandleResize(int w, int h) {
		INSTANCE.width = w;
		INSTANCE.height = h;
	}
	@Override
	protected int ScreenWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	@Override
	protected int ScreenHeight() {
		return height;
	}

}
