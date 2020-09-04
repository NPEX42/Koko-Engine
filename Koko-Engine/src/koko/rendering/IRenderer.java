package koko.rendering;

public interface IRenderer {
	public void ClearColor(float r, float g, float b, float a);

	public void Draw(float[] vertices, int[] triangles);
}
