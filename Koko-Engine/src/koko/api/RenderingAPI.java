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

	protected abstract void UploadMaterial(Material mat);

	protected abstract void UploadPerspectiveProj(float aspect, float fovy, float near, float far);

	protected abstract void UploadTransform(float x, float y, float z, float xr, float yr, float zr, float xs, float ys, float zs);

	protected abstract void DrawArrays(float[] positions);

	protected abstract void UploadOrthoProj(float width, float height, float near, float far);

	public void SetIdentityProj() {
		// TODO Auto-generated method stub
		
	}

}
