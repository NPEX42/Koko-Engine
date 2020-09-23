package koko.rendering;

import java.awt.Color;

import koko.api.HostWindowType;

public abstract class RenderingAPI {
	
	public static HostWindowType host;
	
	public abstract void Background(Color c);
	
	public static RenderingAPI Create() {
		return new RenderingManager();
	}

	public abstract void Draw(float[] vertices, int[] triangles);

	public abstract void LoadShader(String vertexPath, String fragmentPath);

	public abstract String GetRendererVersion();

	public abstract void UploadMaterial(Material mat);

	public abstract void UploadPerspectiveProj(float aspect, float fovy, float near, float far);

	public abstract void UploadTransform(float x, float y, float z, float xr, float yr, float zr, float xs, float ys, float zs);

	public abstract void DrawArrays(float[] positions);

	public abstract void UploadOrthoProj(float width, float height, float near, float far);

	public abstract void SetIdentityProj();

}
