package koko.api;

import java.awt.Color;
import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsfml.window.event.Event;

import koko.events.EventSystem;
import koko.events.ICloseHandler;

public abstract class KokoAPI {
	private RenderingAPI rendering;
	private DisplayAPI display;
	
	private int frameCount = 0;
	
	private static final Logger logger = Logger.getLogger(KokoAPI.class);
	
	public abstract boolean OnUpdate(float ts);
	public abstract boolean OnDestroy();
	public abstract boolean OnCreate();
	
	static {
		if(new File("assets/l4j.txt").exists()) {
			PropertyConfigurator.configure("assets/l4j.txt");
		} else {
			BasicConfigurator.configure();
		}
	}
	
	public void Init(int width, int height, String title, boolean vsync) {
		display = DisplayManager.INSTANCE;
		logger.info("Creating Window...");
		display.CreateDisplay(width, height, title, vsync);
		rendering = RenderingAPI.Create();
		GameLoop();
	}
	
	private void GameLoop() {
		if(!OnCreate()) { display.DestroyDisplay(); System.exit(1); }
		long tp1, tp2 = 0;
		
		
		while(true) {
			tp1 = System.currentTimeMillis();
			if(!OnUpdate((tp2 - tp1) / 1000f)) break;
			if(!display.UpdateDisplay()) break;
			frameCount++;
			tp2 = System.currentTimeMillis();
			
			if(frameCount % 60 == 0) logger.info("Frame Delta: "+(tp2 - tp1)+"ms - "+((Runtime.getRuntime().freeMemory() / 1024) / 1024)+" MB Free - Tick #"+frameCount);
		}
		
		OnDestroy();
		display.DestroyDisplay();
	}
	
	public void InitGLFW(int width, int height, String title, boolean vsync) {
		display = DisplayManager.INSTANCE;
		display.CreateDisplay_GLFW(width, height, title, vsync);
		rendering = RenderingAPI.Create();
		GameLoop();
	}
	
	public void InitSFML(int width, int height, String title, boolean vsync) {
		display = DisplayManager.INSTANCE;
		display.CreateDisplay_SFML(width, height, title, vsync);
		rendering = RenderingAPI.Create();
		GameLoop();
	}
	
	public void Background(Color c) {
		rendering.Background(c);
	}
	
	public void DrawMeshData(float[] vertices, int[] triangles) {
		rendering.Draw(vertices, triangles);
	}
	
	public void RegisterEventHandler(ICloseHandler handler) {
		EventSystem.RegisterCloseHandler(handler);
	}
	
	public void LogInfo(String message) {
		logger.info(message);
	}
}
