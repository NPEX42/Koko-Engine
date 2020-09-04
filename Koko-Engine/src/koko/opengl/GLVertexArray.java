package koko.opengl;

import koko.rendering.VertexArray;
import static org.lwjgl.opengl.GL46.*;
public class GLVertexArray extends VertexArray {

	@Override
	public void Bind() {
		glBindVertexArray(ID);
	}

	@Override
	public void Unbind() {
		glBindVertexArray(ID);
	}

	@Override
	public void Delete() {
		glDeleteVertexArrays(ID);
	}

	@Override
	public void Regenerate() {
		Delete();
		ID = glGenVertexArrays();
		Bind();
	}

}
