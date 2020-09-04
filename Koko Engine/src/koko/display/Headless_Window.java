package koko.display;

import org.apache.log4j.Logger;

public class Headless_Window extends Window {
	private static final Logger logger = Logger.getLogger(Headless_Window.class);
	@Override
	public boolean OpenDisplay(int width, int height, String title, boolean vsync) {
		logger.info("Opened A Headless Window...");
		return true;
	}

	@Override
	public boolean UpdateDisplay() {
		return true;
	}

	@Override
	public boolean CloseDisplay() {
		logger.info("Closed Headless Window...");
		return true;
	}

	@Override
	public boolean IsValid() {
		return true;
	}

	@Override
	public boolean HasGLContext() {
		return false;
	}

}
