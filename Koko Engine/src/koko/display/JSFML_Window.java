package koko.display;

import org.apache.log4j.Logger;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.ContextSettings;
import org.jsfml.window.VideoMode;
import org.jsfml.window.WindowStyle;
import org.jsfml.window.event.Event;
import org.lwjgl.opengl.GL;

import koko.api.RenderingAPI;
import koko.api.HostWindowType;

public class JSFML_Window extends Window {
	private RenderWindow window;
	private boolean isLinked = true;
	private static final Logger logger = Logger.getLogger(JSFML_Window.class);
	@Override
	public boolean OpenDisplay(int width, int height, String title, boolean vsync) {
		try {
		ContextSettings setting = new ContextSettings(4, 24, 4, 6, 8);
		this.window = new RenderWindow(new VideoMode(width, height), title,WindowStyle.DEFAULT, setting);
		window.setVerticalSyncEnabled(vsync);
		GL.createCapabilities();
		
		logger.info("Opened A JSFML Window...");
		RenderingAPI.host = HostWindowType.SFML;
		} catch (UnsatisfiedLinkError ule) {
			isLinked = false;
			return false;
		}
		return true;
	}

	@Override
	public boolean UpdateDisplay() {
		window.display();
		Event event;
		while((event = window.pollEvent()) != null) {
			switch(event.type) {
			case CLOSED: return false;
			default: return true;
			}
		}
		return true;
	}

	@Override
	public boolean CloseDisplay() {
		window.close();
		logger.info("Closed JSFML Window...");
		return true;
	}

	@Override
	public boolean IsValid() {
		return window != null && isLinked;
	}

	@Override
	public boolean HasGLContext() {
		return true;
	}
	
	

}
