package koko.api;

import java.awt.Color;

import koko.rendering.IRenderer;
import koko.rendering.RendererFactory;

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
	protected void Draw(float[] vertices, int[] triangles) {
		renderer.Draw(vertices, triangles);
	}

}
