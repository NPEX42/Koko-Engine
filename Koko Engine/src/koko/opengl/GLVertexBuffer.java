package koko.opengl;

import koko.rendering.VertexBuffer;
import static org.lwjgl.opengl.GL46.*;
public class GLVertexBuffer extends VertexBuffer {

	@Override
	public void Bind() {
		glBindBuffer(GL_ARRAY_BUFFER, ID);
	}

	@Override
	public void Unbind() {
		glBindBuffer(GL_ARRAY_BUFFER, 0);
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

	@Override
	public void SetFloatData(int index, float[] data, int size) {
		Regenerate();
		glBufferData(GL_ARRAY_BUFFER, data, GL_STATIC_DRAW);
		glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(index);
	}
	
}
