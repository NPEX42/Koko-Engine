package koko.display;

public abstract class Window {
	public static Window New(int width, int height, String title, boolean vsync) {
		Window window = new GLFW_Window();
		window.OpenDisplay(width, height, title, vsync);
		
		if(!window.IsValid()) {
			window = new JSFML_Window();
			window.OpenDisplay(width, height, title, vsync);
		}
		
		if(!window.IsValid()) {
			window = new Headless_Window();
			window.OpenDisplay(width, height, title, vsync);
		}
		
		return window;
	}
	
	public abstract boolean OpenDisplay(int width, int height, String title, boolean vsync);
	public abstract boolean UpdateDisplay();
	public abstract boolean CloseDisplay();
	
	public abstract boolean IsValid();
	
	public abstract boolean HasGLContext();
}
