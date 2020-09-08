package koko.rendering;


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
}
