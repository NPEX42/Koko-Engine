package koko.api;

import java.awt.Color;

public abstract class RenderingAPI {
	
	public static HostWindowType host;
	
	public abstract void Background(Color c);
	
	public static RenderingAPI Create() {
		return new RenderingManager();
	}

	protected abstract void Draw(float[] vertices, int[] triangles);

	protected abstract void LoadShader(String vertexPath, String fragmentPath);

	protected abstract String GetRendererVersion();
}
