package koko.rendering;

import java.awt.Color;
import java.util.HashMap;

import org.joml.*;

public abstract class Shader extends Bindable {
	protected int programID, vertexID, fragmentID;
	protected String vertexSource, fragmentSource;
	public Shader(String vertexSource, String fragmentSource) {
		super();
		this.vertexSource = vertexSource;
		this.fragmentSource = fragmentSource;
		
		Regenerate();
		
	}
	
	protected abstract void CheckShaderStatus(int id);

	protected abstract boolean HasValidSource();
	
	protected abstract void GenerateShaders();
	
	protected abstract int GetUniformByName(String name);
	
	public abstract void UploadMat4(String name, Matrix4f mat);
	public abstract void UploadMat3(String name, Matrix3f mat);
	public abstract void UploadMat2(String name, Matrix2f mat);
	
	public abstract void UploadVec4(String name, Vector4f vec);
	public abstract void UploadVec3(String name, Vector3f vec);
	public abstract void UploadVec2(String name, Vector2f vec);
	
	public abstract void UploadColor(String name, Color c);
	
	public abstract HashMap<String, Integer> GetUniformList();
	
	public static final String 
	KOKO_UNIFORM_PROJ = "_Proj",
	KOKO_UNIFORM_TRANSFORM = "_Transform",
	KOKO_UNIFORM_TINT = "_Tint";
}
