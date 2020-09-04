package koko.display;
import static org.lwjgl.glfw.GLFW.*;
import org.apache.log4j.Logger;
import org.lwjgl.opengl.GL;

import koko.api.RenderingAPI;
import koko.api.HostWindowType;
public class GLFW_Window extends Window {
	private static final Logger logger = Logger.getLogger(GLFW_Window.class);
	private long windowID;
	private boolean isLinked = true;
	
	
	@Override
	public boolean OpenDisplay(int width, int height, String title, boolean vsync) {
		try {
		if(!glfwInit()) return false;
		windowID = glfwCreateWindow(width, height, title, 0, 0);
		if(windowID == 0) return false;
		glfwMakeContextCurrent(windowID);
		glfwSwapInterval((vsync) ? 1 : 0);
		GL.createCapabilities();
		logger.info("Opened A GLFW Window...");
		
		RenderingAPI.host = HostWindowType.GLFW;
		} catch(UnsatisfiedLinkError ule) {
			isLinked = false;
			return false;
		}
		return true;
	}

	@Override
	public boolean UpdateDisplay() {
		glfwSwapBuffers(windowID);
		glfwPollEvents();
		return !glfwWindowShouldClose(windowID);
	}

	@Override
	public boolean CloseDisplay() {
		glfwDestroyWindow(windowID);
		glfwTerminate();
		logger.info("Closed GLFW Window...");
		return true;
	}

	@Override
	public boolean IsValid() {
		return windowID != 0 && isLinked; 
	}

	@Override
	public boolean HasGLContext() {
		return true;
	}

}
