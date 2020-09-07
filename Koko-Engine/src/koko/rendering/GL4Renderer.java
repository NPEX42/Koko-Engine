package koko.rendering;
import static org.lwjgl.opengl.GL46.*;

import koko.events.EventSystem;
import koko.opengl.GLIndexBuffer;
import koko.opengl.GLVertexArray;
import koko.opengl.GLVertexBuffer;
public class GL4Renderer implements IRenderer {

	static {
		EventSystem.RegisterResizeHandler(GL4Renderer::OnResize);
	}
	
	@Override
	public void ClearColor(float r, float g, float b, float a) {
		glClearColor(r, g, b, a);
		glClear(GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void Draw(float[] vertices, int[] triangles) {
		GLVertexArray vao = new GLVertexArray();
		vao.Regenerate();
		
		GLVertexBuffer buffer = new GLVertexBuffer();	
		buffer.Regenerate();
		buffer.SetFloatData(0, vertices, 3);
		
		GLIndexBuffer tris = new GLIndexBuffer();
		tris.Regenerate();
		tris.SetTriangles(triangles);
		
		glDrawElements(GL_TRIANGLES, tris.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		buffer.Delete();
		tris.Delete();
		vao.Delete();
	}
	
	private static void OnResize(int w, int h) {
		glViewport(0, 0, w, h);
	}

}
