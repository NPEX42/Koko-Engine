package koko.rendering;

import java.awt.Color;

import org.joml.Matrix4f;

public class RenderingManager extends RenderingAPI {
	IRenderer renderer = RendererFactory.Construct();
	@Override
	public void Background(Color c) {
		renderer.ClearColor(
				c.getRed() / 255f,
				c.getGreen() / 255f,
				c.getBlue() / 255f,
				c.getAlpha() / 255f
		);
	}
	@Override
	public void Draw(float[] vertices, int[] triangles) {
		renderer.Draw(vertices, triangles);
	}
	@Override
	public void LoadShader(String vertexPath, String fragmentPath) {
		renderer.SetActiveShader(vertexPath,fragmentPath);
	}
	@Override
	public String GetRendererVersion() {
		return renderer.GetRendererVersion();
	}
	@Override
	public void UploadMaterial(Material mat) {
		renderer.UploadMaterial(mat);
	}
	@Override
	public void UploadPerspectiveProj(float aspect, float fovy, float near, float far) {
		renderer.SetPerspective(aspect, fovy, near, far);
	}
	@Override
	public void UploadTransform(float x, float y, float z, float xr, float yr, float zr, float xs, float ys, float zs) {
		renderer.SetTransform(x,y,z,xr,yr,zr,xs,ys,zs);
	}
	@Override
	public void DrawArrays(float[] positions) {
		renderer.Draw(positions);
	}
	@Override
	public void UploadOrthoProj(float width, float height, float near, float far) {
		renderer.SetOrthographic(width, height, near, far);
	}
	
	@Override
	public void SetIdentityProj() {
		renderer.SetIdentityProj();
	}

}
