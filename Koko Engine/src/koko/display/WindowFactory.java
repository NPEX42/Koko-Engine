package koko.display;

public class WindowFactory {
	public static Window Create(int width, int height, String title, boolean vsync) {
		return Window.New(width, height, title, vsync);
	}

	public static Window RequestGLFW(int width, int height, String title, boolean vsync) {
		Window w = new GLFW_Window();
		w.OpenDisplay(width, height, title, vsync);
		if(!w.IsValid()) return null;
		return w;
	}

	public static Window RequestSFML(int width, int height, String title, boolean vsync) {
		Window w = new JSFML_Window();
		w.OpenDisplay(width, height, title, vsync);
		if(!w.IsValid()) return null;
		return w;
	}
}
