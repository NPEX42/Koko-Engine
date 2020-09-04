package koko.api;

public abstract class DisplayAPI {
	public abstract void CreateDisplay(int width, int height, String title, boolean vsync);
	public abstract boolean UpdateDisplay();
	public abstract void DestroyDisplay();
	protected abstract void CreateDisplay_GLFW(int width, int height, String title, boolean vsync);
	protected abstract void CreateDisplay_SFML(int width, int height, String title, boolean vsync);
}
