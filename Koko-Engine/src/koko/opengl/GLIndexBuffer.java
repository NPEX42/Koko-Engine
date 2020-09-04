package koko.opengl;


import koko.rendering.IndexBuffer;
import static org.lwjgl.opengl.GL46.*;
public class GLIndexBuffer extends IndexBuffer {
	
	@Override
	public void Bind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ID);
	}

	@Override
	public void Unbind() {
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
	}

	@Override
	public void Delete() {
		glDeleteBuffers(ID);
	}

	@Override
	public void Regenerate() {
		Delete();
		ID = glGenBuffers();
		Bind();
	}
	
	public void SetTriangles(int[] tris) {
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, tris, GL_STATIC_DRAW);
		vertexCount = tris.length;
	}
}
