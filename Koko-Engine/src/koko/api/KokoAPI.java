package koko.api;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import koko.events.EventSystem;
import koko.events.ICloseHandler;
import koko.events.IResizeHandler;
/**
 * @author Npex42
 * @version 0.5.0-alpha
 */
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
	
	/**
	 * @param width - The Width of the window to create
	 * @param height - The Height of the window to create
	 * @param title - The Title of the window to create
	 * @param vsync - Limit the framerate to every v-blank
	 */
	public void Init(int width, int height, String title, boolean vsync) {
		display = DisplayManager.INSTANCE;
		logger.info("Creating Window...");
		display.CreateDisplay(width, height, title, vsync);
		rendering = RenderingAPI.Create();
		logger.info("Renderer Version: "+rendering.GetRendererVersion());
		EngineLogger.coreLogger.setLevel(Level.INFO);
		GameLoop();
	}
	
	/**
	 * Called once, Goes into a loop until the window is closed.
	 */
	private void GameLoop() {
		if(!OnCreate()) { display.DestroyDisplay(); System.exit(1); }
		long tp1, tp2 = 0;
		
		
		while(true) {
			tp1 = System.currentTimeMillis();
			if(!OnUpdate((tp2 - tp1))) break;
			if(!display.UpdateDisplay()) break;
			frameCount++;
			tp2 = System.currentTimeMillis();
			
			if(frameCount % 60 == 0) logger.info("Frame Delta: "+(tp2 - tp1)+"ms - "+((Runtime.getRuntime().freeMemory() / 1024) / 1024)+" MB Free - Tick #"+frameCount);
		}
		
		OnDestroy();
		display.DestroyDisplay();
	}
	
	/**
	 * Initializes a GLFW Window
	 * 
	 * @param width - The Width of the window to create
	 * @param height - The Height of the window to create
	 * @param title - The Title of the window to create
	 * @param vsync - Limit the framerate to every v-blank
	 */
	public void InitGLFW(int width, int height, String title, boolean vsync) {
		display = DisplayManager.INSTANCE;
		display.CreateDisplay_GLFW(width, height, title, vsync);
		rendering = RenderingAPI.Create();
		GameLoop();
	}
	
	/**
	 * Initializes a SFML Window
	 * @param width - The Width of the window to create
	 * @param height - The Height of the window to create
	 * @param title - The Title of the window to create
	 * @param vsync - Limit the framerate to every v-blank
	 */
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
	
	public void DrawMeshData(MeshData data) {
		if(data == null) return;
		if(data.triangles == null) {
			rendering.DrawArrays(data.positions);
		} else {
			DrawMeshData(data.positions, data.triangles);
		}
	}
	
	@Deprecated
	public MeshData LoadOBJ(String path) {
		try {
			return ObjLoader.LoadFromStream(new FileInputStream(path));
		} catch (FileNotFoundException e) {
			return null;
		}
	}
	
	public void LoadShader(String vertexPath, String fragmentPath) {
		rendering.LoadShader(vertexPath, fragmentPath);
	}
	
	public void RegisterCloseEventHandler(ICloseHandler handler) {
		EventSystem.RegisterCloseHandler(handler);
	}
	
	public void RegisterResizeEventHandler(IResizeHandler handler) {
		EventSystem.RegisterResizeHandler(handler);
	} 
	
	public void UploadMaterial(Material mat) {
		rendering.UploadMaterial(mat);
	}
	
	public void UploadPerspectiveProj(float aspect, float fovy, float near, float far) {
		rendering.UploadPerspectiveProj(aspect, fovy, near, far);
	}
	
	public void SetOrthoProj(float width, float height, float near, float far) {
		rendering.UploadOrthoProj(width, height, near, far);
	}
	
	public void SetTransform(float x, float y, float z) {
		rendering.UploadTransform(x,y,z,0,0,0,1,1,1);
	}
	
	public void SetTransform(float x, float y, float z, float xr, float yr, float zr) {
		rendering.UploadTransform(x,y,z,xr,yr,zr,1,1,1);
	}
	
	public void SetTransform(float x, float y, float z, float xr, float yr, float zr, float xs, float ys, float zs) {
		rendering.UploadTransform(x,y,z,xr,yr,zr,xs,ys,zs);
	}
	
	public void UseIdentityProj() {
		rendering.SetIdentityProj();
	}
	
	public void LogInfo(String message) {
		logger.info(message);
	}
	
	public int ScreenWidth() { return display.ScreenWidth(); }
	public int ScreenHeight() { return display.ScreenHeight(); }
	
	public float ScreenAspect() { return (float) ScreenWidth() / (float) ScreenHeight(); }
}
