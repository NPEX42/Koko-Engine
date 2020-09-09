package koko.rendering;

import koko.api.Material;

public interface IRenderer {
	public void ClearColor(float r, float g, float b, float a);

	public void Draw(float[] vertices, int[] triangles);

	public void SetActiveShader(String vertexPath, String fragmentPath);
	
	public String GetRendererVersion();

	public void UploadMaterial(Material mat);

	public void SetPerspective(float aspect, float fovy, float near, float far);

	public void SetTransform(float x, float y, float z, float xr, float yr, float zr, float xs, float ys, float zs);

	public void Draw(float[] positions);
}
