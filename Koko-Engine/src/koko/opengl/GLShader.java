package koko.opengl;

import koko.api.EngineLogger;
import koko.rendering.Shader;
import np.core.IO;

import static org.lwjgl.opengl.GL46.*;
public class GLShader extends Shader {

	public GLShader(String vertexPath, String fragmentPath) {
		super(IO.LoadString(vertexPath), IO.LoadString(fragmentPath));
	}

	@Override
	public void Bind() {
		glUseProgram(programID);
	}

	@Override
	public void Unbind() {
		glUseProgram(0);
	}

	@Override
	public void Delete() {
		glDeleteShader(vertexID);
		glDeleteShader(fragmentID);
		glDeleteProgram(programID);
	}

	@Override
	public void Regenerate() {
		Delete();
		GenerateShaders();
		Compile();
		Bind();
	}
	
	public void Compile() {
		if(HasValidSource()) {
			GenerateShaders();
			glShaderSource(vertexID, vertexSource);
			glShaderSource(fragmentID, fragmentSource);
			
			glCompileShader(vertexID);
			glCompileShader(fragmentID);
			
			CheckShaderStatus(vertexID);
			CheckShaderStatus(fragmentID);
			
			glAttachShader(programID, vertexID);
			glAttachShader(programID, fragmentID);
			
			glLinkProgram(programID);
		}
	}
	
	public void CheckShaderStatus(int id) {
		if(glGetShaderi(id, GL_COMPILE_STATUS) == GL_FALSE) {
			EngineLogger.coreLogger.warn("Shader Failed To Compile...");
			EngineLogger.coreLogger.warn(glGetShaderInfoLog(id,1024));
		} else { EngineLogger.coreLogger.warn("Shader Compiled Successfully..."); }
	}

	protected boolean HasValidSource() {
		return !(vertexSource == null & fragmentSource == null);
	}
	
	protected void GenerateShaders() {
		vertexID = glCreateShader(GL_VERTEX_SHADER);
		fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
		programID = glCreateProgram();
	}

}
