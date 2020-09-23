package koko.events;

import java.util.ArrayList;
import java.util.List;

import koko.api.EngineLogger;

public class EventSystem {
	
	private static List<ICloseHandler> closeHandlers = new ArrayList<ICloseHandler>();
	private static List<IResizeHandler> resizeHandlers = new ArrayList<IResizeHandler>();
	
	private static List<ICustomHandler> customHandlers = new ArrayList<ICustomHandler>();
	
	public synchronized static void DispatchCloseEvent() {
		EngineLogger.coreLogger.info("Dispatching Close Event...");
		for(ICloseHandler handler : closeHandlers) {
			handler.Handle();
		}
	}
	
	public static void glfw_DispatchCloseEvent(long window) {
		DispatchCloseEvent();
	}
	
	public synchronized static void DispatchResizeEvent(int w, int h) {
		EngineLogger.coreLogger.info("Dispatching Resize Event (w,h): ("+w+"x"+h+")");
		for(IResizeHandler handler : resizeHandlers) {
			handler.Resize(w,h);
		}
	}
	
	public static void glfw_DispatchResizeEvent(long window, int w, int h) {
		DispatchResizeEvent(w,h);
	}
	
	public synchronized static void DispatchCustomEvent(CustomEvent<?> event) {
		EngineLogger.coreLogger.info("Dispatching Custom Event...");
		for(ICustomHandler handler : customHandlers) {
			handler.Handle(event);
		}
	}
	
	
	
	public  static void RegisterCloseHandler(ICloseHandler handler) {
		closeHandlers.add(handler);
		//EngineLogger.coreLogger.info("Added Close Handler (0x"+BaseConverter.toHexString(closeHandlers.size(), 4)+")");
	}
	
	public static void RegisterResizeHandler(IResizeHandler handler) {
		resizeHandlers.add(handler);
		//EngineLogger.coreLogger.info("Added Resize Handler (0x"+BaseConverter.toHexString(resizeHandlers.size(), 4)+")");
	}
	
	public static void RegisterCustomHandler(ICustomHandler handler) {
		customHandlers.add(handler);
		//EngineLogger.coreLogger.info("Added Custom Handler (0x"+BaseConverter.toHexString(customHandlers.size(), 4)+")");
	}
}
