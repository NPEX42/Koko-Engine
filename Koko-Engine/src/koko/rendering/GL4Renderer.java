package koko.rendering;
import static org.lwjgl.opengl.GL46.*;

import org.joml.Matrix4f;

import koko.api.EngineLogger;
import koko.events.EventSystem;
import koko.opengl.GLIndexBuffer;
import koko.opengl.GLShader;
import koko.opengl.GLVertexArray;
import koko.opengl.GLVertexBuffer;
public class GL4Renderer implements IRenderer {

	private GLShader shader;
	
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
		
		if(shader != null) {
			shader.Bind();
		}
		
		glDrawElements(GL_TRIANGLES, tris.getVertexCount(), GL_UNSIGNED_INT, 0);
		
		buffer.Delete();
		tris.Delete();
		vao.Delete();
	}
	
	private static void OnResize(int w, int h) {
		glViewport(0, 0, w, h);
	}

	@Override
	public void SetActiveShader(String vertexPath, String fragmentPath) {
		shader = new GLShader(vertexPath, fragmentPath);
	}

	@Override
	public String GetRendererVersion() {
		return glGetString(GL_VERSION)+" | "+glGetString(GL_RENDERER);
	}

	@Override
	public void UploadMaterial(Material mat) {
		if(shader != null) {
			EngineLogger.coreLogger.debug("Uploading Material...");
			shader.UploadColor(Shader.KOKO_UNIFORM_TINT, mat.tint);
		}
	}

	@Override
	public void SetPerspective(float aspect, float fovy, float near, float far) {
		if(shader != null) {
			EngineLogger.coreLogger.debug("Setting Perspective ("+aspect+","+fovy+","+near+","+far+")");
			shader.UploadMat4(Shader.KOKO_UNIFORM_PROJ, new Matrix4f().perspective(fovy, aspect, near, far));
		}
	}

	@Override
	public void SetTransform(float x, float y, float z, float xr, float yr, float zr, float xs, float ys, float zs) {
		if(shader != null) {
			EngineLogger.coreLogger.debug("Setting Transform ("+x+","+y+","+z+","+xr+","+yr+","+zr+","+xs+","+ys+","+zs+")");
			Matrix4f mat = new Matrix4f();
			mat.translate(x,y,z);
			mat.rotate(xr,1,0,0);
			mat.rotate(yr,0,1,0);
			mat.rotate(zr,0,0,1);
			mat.scale(xs,ys,zs);
			shader.UploadMat4(Shader.KOKO_UNIFORM_TRANSFORM, mat);
		}
	}

	@Override
	public void Draw(float[] positions) {
		GLVertexArray vao = new GLVertexArray();
		vao.Regenerate();
		
		GLVertexBuffer buffer = new GLVertexBuffer();	
		buffer.Regenerate();
		buffer.SetFloatData(0, positions, 3);
		
		
		if(shader != null) {
			shader.Bind();
		}
		
		glDrawArrays(GL_TRIANGLES, 0, positions.length);
		
		buffer.Delete();
		vao.Delete();
	}

	@Override
	public void SetOrthographic(float width, float height, float near, float far) {
		shader.UploadMat4(Shader.KOKO_UNIFORM_PROJ, new Matrix4f().ortho(0, width, height, 0, near, far));
	}
	
	@Override
	public void SetIdentityProj() {
		shader.UploadMat4(Shader.KOKO_UNIFORM_PROJ, new Matrix4f());
	}

}
