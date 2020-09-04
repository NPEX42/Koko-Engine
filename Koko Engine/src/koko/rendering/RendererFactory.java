package koko.rendering;

public class RendererFactory {
	public static IRenderer Construct() {
		return new GL4Renderer();
	}
}
