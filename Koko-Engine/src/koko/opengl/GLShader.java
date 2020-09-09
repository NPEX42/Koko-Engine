package koko.opengl;

import koko.api.EngineLogger;
import koko.rendering.Shader;
import np.core.IO;

import static org.lwjgl.opengl.GL46.*;

import java.awt.Color;
import java.util.HashMap;

import org.joml.*;

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

	@Override
	protected int GetUniformByName(String name) {
		if(glGetUniformLocation(programID, name) == -1) EngineLogger.coreLogger.error("Unable To Locate Uniform '"+name+"'...");
		return glGetUniformLocation(programID, name);
	}

	@Override
	public void UploadMat4(String name, Matrix4f mat) {
		glUniformMatrix4fv(GetUniformByName(name), false, mat.get(new float[16]));
	}

	@Override
	public void UploadMat3(String name, Matrix3f mat) {
		glUniformMatrix3fv(GetUniformByName(name), false, mat.get(new float[9]));
	}

	@Override
	public void UploadMat2(String name, Matrix2f mat) {
		glUniformMatrix2fv(GetUniformByName(name), false, mat.get(new float[4]));
	}

	@Override
	public void UploadVec4(String name, Vector4f vec) {
		glUniform4f(GetUniformByName(name), vec.x, vec.y, vec.z, vec.w);
	}

	@Override
	public void UploadVec3(String name, Vector3f vec) {
		glUniform3f(GetUniformByName(name), vec.x, vec.y, vec.z);
		
	}

	@Override
	public void UploadVec2(String name, Vector2f vec) {
		glUniform2f(GetUniformByName(name), vec.x, vec.y);
		
	}

	@Override
	public HashMap<String, Integer> GetUniformList() {
		return null;
	}

	@Override
	public void UploadColor(String name, Color c) {
		Vector4f v = new Vector4f();
		v.x = c.getRed() / 255f;
		v.y = c.getGreen() / 255f;
		v.z = c.getBlue() / 255f;
		v.w = c.getAlpha() / 255f;
		UploadVec4(name, v);
	}

}
